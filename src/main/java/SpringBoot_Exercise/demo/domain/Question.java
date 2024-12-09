package SpringBoot_Exercise.demo.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@EqualsAndHashCode(of = "id")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 200)
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String content;
    private Long authorId;
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;

    protected Question() {}

    private Question(String subject, String content, LocalDateTime createDate, Long authorId){
        this.subject = subject;
        this.content = content;
        this.createDate = createDate;
        this.authorId = authorId;
    }

    public static Question createQuestion(String subject, String content, LocalDateTime createDate,  Long authorId) {
        return new Question(subject, content, createDate, authorId);
    }

    public void modifyQuestion(String subject, String content, LocalDateTime now) {
        this.subject = subject;
        this.content = content;
        this.createDate = now;
    }
}
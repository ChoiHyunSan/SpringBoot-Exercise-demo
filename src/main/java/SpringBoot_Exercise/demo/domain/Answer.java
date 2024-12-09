package SpringBoot_Exercise.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "TEXT")
    private String content;
    private LocalDateTime createDate;

    private Long authorId;

    @ManyToOne
    private Question question;

    public static Answer CreateAnswer(String content, LocalDateTime time, Question question, Long authorId) {
        return new Answer(content, time, question, authorId);
    }

    protected Answer() {}

    public Answer(String content, LocalDateTime time, Question question, Long authorId) {
        this.content = content;
        this.createDate = time;
        this.question = question;
        this.authorId = authorId;
    }

    public static Answer createAnswer(String content, LocalDateTime time, Question question, Long authorId) {
        Answer answer = new Answer(content, time, question, authorId);
        question.getAnswerList().add(answer);
        return answer;
    }
}
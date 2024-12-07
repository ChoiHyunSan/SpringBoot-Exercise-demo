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

    @ManyToOne
    private Question question;

    public static Answer CreateAnswer(String content, LocalDateTime time, Question question) {
        return new Answer(content, time, question);
    }

    protected Answer() {}

    public Answer(String content, LocalDateTime time, Question question) {
        this.content = content;
        this.createDate = time;
        this.question = question;
    }

    public static Answer createAnswer(String content, LocalDateTime time, Question question) {
        Answer answer = new Answer(content, time, question);
        question.getAnswerList().add(answer);
        return answer;
    }
}
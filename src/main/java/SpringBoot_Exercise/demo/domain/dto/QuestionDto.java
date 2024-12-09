package SpringBoot_Exercise.demo.domain.dto;

import SpringBoot_Exercise.demo.domain.Answer;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class QuestionDto {
    private Integer id;
    private String subject;
    private String content;
    private String authorName;
    private LocalDateTime createDate;
    private List<AnswerDto> answerList;

    public QuestionDto(Integer id, String subject, String content, LocalDateTime createDate, String authorName, List<AnswerDto> answerList) {
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.createDate = createDate;
        this.authorName = authorName;
        this.answerList = answerList;
    }
}

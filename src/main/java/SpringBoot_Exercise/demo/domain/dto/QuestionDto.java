package SpringBoot_Exercise.demo.domain.dto;

import SpringBoot_Exercise.demo.domain.Question;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuestionDto {
    @NotEmpty(message="제목은 필수입니다.")
    @Size(max=200)
    private String subject;
    @NotEmpty(message="제목은 필수입니다.")
    private String content;

    public QuestionDto(Question question) {
        this.subject = question.getSubject();
        this.content = question.getContent();
    }
}

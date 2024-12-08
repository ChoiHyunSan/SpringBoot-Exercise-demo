package SpringBoot_Exercise.demo.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AnswerDto {
    @NotEmpty(message="내용은 필수입니다.")
    private String content;

    AnswerDto(String content) {
        this.content = content;
    }
}

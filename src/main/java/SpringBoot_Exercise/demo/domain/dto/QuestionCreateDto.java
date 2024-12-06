package SpringBoot_Exercise.demo.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuestionCreateDto {
    private String subject;
    private String content;
}

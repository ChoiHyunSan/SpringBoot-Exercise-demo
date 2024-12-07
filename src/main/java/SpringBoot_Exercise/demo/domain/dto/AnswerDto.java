package SpringBoot_Exercise.demo.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class AnswerDto {
    private String content;

    AnswerDto(String content) {
        this.content = content;
    }
}

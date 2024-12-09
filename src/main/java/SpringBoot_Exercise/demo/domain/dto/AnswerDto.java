package SpringBoot_Exercise.demo.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class AnswerDto {
    private String content;
    private LocalDateTime createDate;
    private String authorName;

    public AnswerDto(String content, LocalDateTime createDate, String authorName) {
        this.content = content;
        this.createDate = createDate;
        this.authorName = authorName;
    }
}

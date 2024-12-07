package SpringBoot_Exercise.demo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice(basePackages = {"SpringBoot_Exercise.demo.controller"})
public class GlobalExceptionHandler {
    @ExceptionHandler(DataNotFoundException.class)
    public String handleDataNotFoundException(DataNotFoundException e) {

        // 예외 발생 시 로그 기록
        log.error("Resource not found: {}", e.getMessage());

        // 사용자를 목록 페이지로 리다이렉트
        return "redirect:/question/list";
    }
}

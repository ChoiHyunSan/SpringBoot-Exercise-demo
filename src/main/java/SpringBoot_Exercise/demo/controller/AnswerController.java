package SpringBoot_Exercise.demo.controller;

import SpringBoot_Exercise.demo.domain.dto.AnswerDto;
import SpringBoot_Exercise.demo.exception.DataNotFoundException;
import SpringBoot_Exercise.demo.service.AnswerService;
import SpringBoot_Exercise.demo.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {

    private final AnswerService answerService;
    private final QuestionService questionService;

    @PostMapping("/create/{id}")
    public String insert(@PathVariable("id") Integer id,  @RequestBody AnswerDto answerDto) {
        answerService.registerAnswer(id, answerDto);
        return "redirect:/question/list";
    }
}

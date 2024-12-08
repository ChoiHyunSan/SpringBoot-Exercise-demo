package SpringBoot_Exercise.demo.controller;

import SpringBoot_Exercise.demo.domain.Question;
import SpringBoot_Exercise.demo.domain.dto.AnswerDto;
import SpringBoot_Exercise.demo.service.AnswerService;
import SpringBoot_Exercise.demo.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {

    private final AnswerService answerService;
    private final QuestionService questionService;

    @PostMapping("/create/{id}")
    public String insert(@PathVariable("id") Integer id,
                         @Valid @ModelAttribute("answer") AnswerDto answerDto,
                         BindingResult bindingResult,
                         Model model) {
        if(bindingResult.hasErrors()) {
            Question question = questionService.getQuestion(id);
            model.addAttribute("question", question);
            return "question_answer";
        }
        answerService.registerAnswer(id, answerDto.getContent());
        return "redirect:/question/list";
    }
}

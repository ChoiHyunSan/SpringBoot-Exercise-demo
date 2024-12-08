package SpringBoot_Exercise.demo.controller;

import SpringBoot_Exercise.demo.domain.Question;
import SpringBoot_Exercise.demo.domain.dto.AnswerDto;
import SpringBoot_Exercise.demo.domain.dto.QuestionDto;
import SpringBoot_Exercise.demo.service.QuestionService;
import groovy.util.logging.Slf4j;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@lombok.extern.slf4j.Slf4j
@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/list")
    public String list(Model model,
                       @RequestParam(value="page", defaultValue="0") int page) {
        Page<Question> paging = this.questionService.getList(page);
        model.addAttribute("paging", paging);
        return "question_list";
    }

    // GET /api/questions/create - 질문 생성 페이지 요청
    @GetMapping("/create")
    public String createForm(@ModelAttribute("question") QuestionDto question) {
        return "question_form";
    }

    @PostMapping("/create")
    public String create(Model model,
                         @Valid @ModelAttribute("question") QuestionDto dto,
                         BindingResult bindingResult,
                         @RequestParam(value="page", defaultValue="0") int page) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }

        questionService.registerQuestion(dto.getSubject(), dto.getContent());
        Page<Question> paging = this.questionService.getList(page);
        model.addAttribute("paging", paging);
        return "redirect:/question/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id,
                         Model model) {
        Question question = questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    @GetMapping("/answer/create/{id}")
    public String answer(@PathVariable("id") Integer id,
                         Model model) {
        Question question = questionService.getQuestion(id);
        model.addAttribute("question", question);
        model.addAttribute("answer", new AnswerDto());
        return "question_answer";
    }
}

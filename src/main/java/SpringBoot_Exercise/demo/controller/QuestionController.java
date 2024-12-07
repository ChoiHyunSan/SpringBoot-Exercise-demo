package SpringBoot_Exercise.demo.controller;

import SpringBoot_Exercise.demo.domain.Question;
import SpringBoot_Exercise.demo.domain.dto.QuestionDto;
import SpringBoot_Exercise.demo.exception.DataNotFoundException;
import SpringBoot_Exercise.demo.service.QuestionService;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static SpringBoot_Exercise.demo.domain.Question.createQuestion;

@lombok.extern.slf4j.Slf4j
@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Question> questionList = questionService.getList();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }

    // GET /api/questions/create - 질문 생성 페이지 요청
    @GetMapping("/create")
    public String createForm() {
        return "question_form";  // 타임리프 템플릿 이름
    }

    @PostMapping("/create")
    public String create(@RequestBody  QuestionDto dto) {
        questionService.registerQuestion(dto);
        return "redirect:/question/list"; // 성공 시 리다이렉트할 URL 반환
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, Model model) {
        Question question = questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    @GetMapping("/answer/create/{id}")
    public String answer(@PathVariable("id") Integer id, Model model) {
        Question question = questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_answer";
    }
}

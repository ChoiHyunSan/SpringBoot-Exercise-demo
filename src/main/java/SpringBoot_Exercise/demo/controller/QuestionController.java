package SpringBoot_Exercise.demo.controller;

import SpringBoot_Exercise.demo.domain.Question;
import SpringBoot_Exercise.demo.domain.SiteUser;
import SpringBoot_Exercise.demo.domain.dto.AnswerForm;
import SpringBoot_Exercise.demo.domain.dto.QuestionDto;
import SpringBoot_Exercise.demo.domain.dto.QuestionForm;
import SpringBoot_Exercise.demo.exception.DataNotFoundException;
import SpringBoot_Exercise.demo.service.CustomUserDetails;
import SpringBoot_Exercise.demo.service.QuestionService;
import SpringBoot_Exercise.demo.service.UserService;
import groovy.util.logging.Slf4j;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@lombok.extern.slf4j.Slf4j
@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;
    private final UserService userService;

    @GetMapping("/list")
    public String list(Model model,
                       @RequestParam(value="page", defaultValue="0") int page) {
        Page<Question> paging = this.questionService.getList(page);
        model.addAttribute("paging", paging);
        return "question_list";
    }

    // GET /api/questions/create - 질문 생성 페이지 요청
    @GetMapping("/create")
    public String createForm(@ModelAttribute("question") QuestionForm question) {
        return "question_form";
    }

    @PostMapping("/create")
    public String create(Model model,
                         @Valid @ModelAttribute("question") QuestionForm dto,
                         @AuthenticationPrincipal CustomUserDetails userDetails,
                         BindingResult bindingResult,
                         @RequestParam(value="page", defaultValue="0") int page) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }

        SiteUser byEmail = userService.findByEmail(userDetails.getUsername()).get();
        questionService.registerQuestion(dto.getSubject(), dto.getContent(), byEmail.getId());
        Page<Question> paging = this.questionService.getList(page);
        model.addAttribute("paging", paging);
        return "redirect:/question/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id,
                         Model model) {
        Optional<QuestionDto> questionDto = userService.getQuestionDtoById(id);
        if(questionDto.isEmpty()){
            throw new DataNotFoundException("해당 질문이 존재하지 않습니다.");
        }

        model.addAttribute("question", questionDto.get());
        return "question_detail";
    }

    @GetMapping("/answer/create/{id}")
    public String answer(@PathVariable("id") Integer id,
                         Model model) {
        Question question = questionService.getQuestion(id);
        model.addAttribute("question", question);
        model.addAttribute("answer", new AnswerForm());
        return "question_answer";
    }
}

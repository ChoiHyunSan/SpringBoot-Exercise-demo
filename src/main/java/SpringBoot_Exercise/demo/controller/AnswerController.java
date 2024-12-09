package SpringBoot_Exercise.demo.controller;

import SpringBoot_Exercise.demo.domain.Question;
import SpringBoot_Exercise.demo.domain.SiteUser;
import SpringBoot_Exercise.demo.domain.dto.AnswerForm;
import SpringBoot_Exercise.demo.domain.dto.QuestionDto;
import SpringBoot_Exercise.demo.exception.DataNotFoundException;
import SpringBoot_Exercise.demo.service.AnswerService;
import SpringBoot_Exercise.demo.service.CustomUserDetails;
import SpringBoot_Exercise.demo.service.QuestionService;
import SpringBoot_Exercise.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {

    private final AnswerService answerService;
    private final QuestionService questionService;
    private final UserService userService;

    @PostMapping("/create/{id}")
    public String insert(@PathVariable("id") Integer id,
                         @Valid @ModelAttribute("answer") AnswerForm answerDto,
                         @AuthenticationPrincipal CustomUserDetails userDetails,
                         BindingResult bindingResult,
                         Model model) {

        Question question = questionService.getQuestion(id);
        if(bindingResult.hasErrors()) {
            model.addAttribute("question", question);
            return "question_answer";
        }

        SiteUser byEmail = userService.findByEmail(userDetails.getUsername()).get();
        answerService.registerAnswer(id, answerDto.getContent(), byEmail.getId());

        // 갱신된 정보 다시 넘기기
        Optional<QuestionDto> questionDto = userService.getQuestionDtoById(id);
        if(questionDto.isEmpty()){
            throw new DataNotFoundException("해당 질문이 존재하지 않습니다.");
        }

        model.addAttribute("question", questionDto.get());
        return "redirect:/question/detail/{id}";
    }
}

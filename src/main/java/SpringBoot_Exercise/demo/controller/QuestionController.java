package SpringBoot_Exercise.demo.controller;

import SpringBoot_Exercise.demo.domain.Question;
import SpringBoot_Exercise.demo.domain.dto.QuestionCreateDto;
import SpringBoot_Exercise.demo.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static SpringBoot_Exercise.demo.domain.Question.createQuestion;

@RequiredArgsConstructor
@Controller
@RequestMapping("/question")
public class QuestionController {

    private final QuestionRepository questionRepository;

    @GetMapping("/list")
    public String list(Model model) {
        List<Question> questionList = questionRepository.findAll();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }

    // GET /api/questions/create - 질문 생성 페이지 요청
    @GetMapping("/create")
    public String createForm() {
        return "question_form";  // 타임리프 템플릿 이름
    }

    @PostMapping("/create")
    public String create(@RequestBody QuestionCreateDto dto) {
        Question question = createQuestion(
                dto.getSubject(),
                dto.getContent(),
                LocalDateTime.now()
        );
        questionRepository.save(question);
        return "redirect:/question/list";
    }
}

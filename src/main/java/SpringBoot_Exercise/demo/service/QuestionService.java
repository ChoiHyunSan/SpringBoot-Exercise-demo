package SpringBoot_Exercise.demo.service;

import SpringBoot_Exercise.demo.domain.Question;
import SpringBoot_Exercise.demo.domain.dto.QuestionDto;
import SpringBoot_Exercise.demo.exception.DataNotFoundException;
import SpringBoot_Exercise.demo.repository.QuestionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static SpringBoot_Exercise.demo.domain.Question.createQuestion;

@RequiredArgsConstructor
@Service
@Transactional
public class QuestionService {

    private final static int PAGE_SIZE = 5;
    private final QuestionRepository questionRepository;
    public List<Question> getList(){
        return questionRepository.findAll();
    }

    public Question registerQuestion(String subject, String content) {
        Question question = createQuestion(
                subject,
                content,
                LocalDateTime.now()
        );
        return questionRepository.save(question);
    }

    public Question getQuestion(Integer id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Question not found with id " + id));
    }

    public Page<Question> getList(int page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        return this.questionRepository.findAll(pageable);
    }
}

package SpringBoot_Exercise.demo.service;

import SpringBoot_Exercise.demo.domain.Question;
import SpringBoot_Exercise.demo.domain.dto.QuestionDto;
import SpringBoot_Exercise.demo.exception.DataNotFoundException;
import SpringBoot_Exercise.demo.repository.QuestionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static SpringBoot_Exercise.demo.domain.Question.createQuestion;

@RequiredArgsConstructor
@Service
@Transactional
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> getList(){
        return questionRepository.findAll();
    }

    public Question registerQuestion(QuestionDto dto) {
        Question question = createQuestion(
                dto.getSubject(),
                dto.getContent(),
                LocalDateTime.now()
        );
        return questionRepository.save(question);
    }

    public Question getQuestion(Integer id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Question not found with id " + id));
    }
}

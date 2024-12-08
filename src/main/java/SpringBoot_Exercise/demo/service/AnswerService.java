package SpringBoot_Exercise.demo.service;

import SpringBoot_Exercise.demo.domain.Answer;
import SpringBoot_Exercise.demo.domain.Question;
import SpringBoot_Exercise.demo.domain.dto.AnswerDto;
import SpringBoot_Exercise.demo.repository.AnswerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionService questionService;

    public Answer registerAnswer(Integer questionId, String content) {
        Question question = questionService.getQuestion(questionId);
        Answer answer = Answer.createAnswer(
                content,
                LocalDateTime.now(),
                question
        );
        return answerRepository.save(answer);
    }
}

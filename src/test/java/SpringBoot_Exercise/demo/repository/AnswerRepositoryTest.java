package SpringBoot_Exercise.demo.repository;

import SpringBoot_Exercise.demo.domain.Answer;
import SpringBoot_Exercise.demo.domain.Question;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static SpringBoot_Exercise.demo.domain.Question.createQuestion;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AnswerRepositoryTest {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Test
    void save(){
        Question question1 = createQuestion(
                "subject1",
                "content1",
                LocalDateTime.now()
        );
        questionRepository.save(question1);

        Answer answer = Answer.CreateAnswer("content", LocalDateTime.now(), question1);
        answerRepository.save(answer);

        Assertions.assertEquals(1, answerRepository.count());

    }
}
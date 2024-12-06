package SpringBoot_Exercise.demo.repository;

import SpringBoot_Exercise.demo.domain.Question;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static SpringBoot_Exercise.demo.domain.Question.createQuestion;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class QuestionRepositoryTest {
    @Autowired
    QuestionRepository questionRepository;

    @Test
    public void testJPA(){
        Question question1 = createQuestion(
                "subject1",
                "content1",
                LocalDateTime.now()
        );

        Question question2 = createQuestion(
                "subject2",
                "content2",
                LocalDateTime.now()
        );

        questionRepository.save(question1);
        questionRepository.save(question2);

        assertThat(questionRepository.findAll()).hasSize(2);
        assertThat(questionRepository.findById(question1.getId()).orElse(null)).isEqualTo(question1);
        assertThat(questionRepository.findById(question2.getId()).orElse(null)).isEqualTo(question2);
        assertThat(questionRepository.count()).isEqualTo(2);
    }

    @Test
    void findAll(){
        Question question1 = createQuestion(
                "subject1",
                "content1",
                LocalDateTime.now()
        );

        Question question2 = createQuestion(
                "subject2",
                "content2",
                LocalDateTime.now()
        );

        questionRepository.save(question1);
        questionRepository.save(question2);

        List<Question> list = questionRepository.findAll();
        assertThat(list).hasSize(2);
        assertThat(list.get(0).getContent()).isEqualTo("content1");
        assertThat(list.get(1).getContent()).isEqualTo("content2");
    }

    @Test
    void findById(){
        Question question1 = createQuestion(
                "subject1",
                "content1",
                LocalDateTime.now()
        );

        Question question2 = createQuestion(
                "subject2",
                "content2",
                LocalDateTime.now()
        );

        Optional<Question> oq = questionRepository.findById(1);
        if(oq.isPresent()) {
            Question q = oq.get();
            assertEquals("sebject1", q.getSubject());
        }
    }

    @Test
    void findBySubject(){
        Question question1 = createQuestion(
                "subject1",
                "content1",
                LocalDateTime.now()
        );

        Question question2 = createQuestion(
                "subject2",
                "content2",
                LocalDateTime.now()
        );

        questionRepository.save(question1);
        questionRepository.save(question2);

        Question question = questionRepository.findBySubject("subject1");
        assertThat(question).isNotNull();
        assertThat(question.getContent()).isEqualTo("content1");
    }

    @Test
    void findBySubjectAndContent(){
        Question question1 = createQuestion(
                "subject1",
                "content1",
                LocalDateTime.now()
        );

        Question question2 = createQuestion(
                "subject2",
                "content2",
                LocalDateTime.now()
        );

        questionRepository.save(question1);
        questionRepository.save(question2);

        Question question = questionRepository.findBySubjectAndContent("subject1", "content1");
        assertThat(question).isNotNull();
        assertThat(question.getContent()).isEqualTo("content1");
    }

    @Test
    void update(){
        Question question1 = createQuestion(
                "subject1",
                "content1",
                LocalDateTime.now()
        );

        Question question2 = createQuestion(
                "subject2",
                "content2",
                LocalDateTime.now()
        );

        Question save = questionRepository.save(question1);
        questionRepository.save(question2);

        Optional<Question> oq = questionRepository.findById(save.getId());
        assertTrue(oq.isPresent());
        Question q = oq.get();
        q.modifyQuestion(
                "subject3",
                "content3",
                LocalDateTime.now()
        );
        this.questionRepository.save(q);
        oq = this.questionRepository.findById(save.getId());
        assertTrue(oq.isPresent());
        assertEquals("subject3", q.getSubject());
    }

    @Test
    void delete(){
        Question question1 = createQuestion(
                "subject1",
                "content1",
                LocalDateTime.now()
        );

        Question question2 = createQuestion(
                "subject2",
                "content2",
                LocalDateTime.now()
        );

        Question save = questionRepository.save(question1);
        questionRepository.save(question2);

        Optional<Question> oq = questionRepository.findById(save.getId());
        assertTrue(oq.isPresent());
        Question q = oq.get();
        this.questionRepository.delete(q);
        assertEquals(1, this.questionRepository.count());
    }
}

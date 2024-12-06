package SpringBoot_Exercise.demo.repository;

import SpringBoot_Exercise.demo.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}

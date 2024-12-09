package SpringBoot_Exercise.demo.service;

import SpringBoot_Exercise.demo.domain.Question;
import SpringBoot_Exercise.demo.domain.SiteUser;
import SpringBoot_Exercise.demo.domain.dto.AnswerDto;
import SpringBoot_Exercise.demo.domain.dto.LoginForm;
import SpringBoot_Exercise.demo.domain.dto.QuestionDto;
import SpringBoot_Exercise.demo.repository.QuestionRepository;
import SpringBoot_Exercise.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final QuestionService questionService;

    public SiteUser registerUser(String name, String password, String email){
        return userRepository.save(new SiteUser(name, password, email));
    }

    public boolean login(LoginForm loginForm) {
        Optional<SiteUser> user = userRepository.findByEmail(loginForm.getEmail());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return user.isPresent() && !loginForm.getPassword().equals(passwordEncoder.encode(user.get().getPassword()));
    }

    public Optional<SiteUser> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<SiteUser> findById(Long authorId) {
        return userRepository.findById(authorId);
    }

    public Optional<QuestionDto> getQuestionDtoById(Integer id) {

        Question question = questionService.getQuestion(id);
        Optional<SiteUser> byEmail = findById(question.getAuthorId());
        if(byEmail.isEmpty()){
            return Optional.empty();
        }

        // 답변 리스트 생성
        List<AnswerDto> answerList = question.getAnswerList().stream()
                .map(answer -> {
                    Optional<SiteUser> byId = findById(answer.getAuthorId());
                    if (byId.isEmpty()) {
                        return null;
                    }
                    return new AnswerDto(answer.getContent(), answer.getCreateDate(), byId.get().getName());
                })
                .filter(Objects::nonNull)
                .toList();

        QuestionDto questionDto = new QuestionDto(
                question.getId(),
                question.getSubject(),
                question.getContent(),
                question.getCreateDate(),
                byEmail.get().getName(),
                answerList
        );

        return Optional.of(questionDto);
    }
}

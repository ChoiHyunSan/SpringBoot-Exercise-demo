package SpringBoot_Exercise.demo.service;

import SpringBoot_Exercise.demo.domain.User;
import SpringBoot_Exercise.demo.domain.dto.LoginForm;
import SpringBoot_Exercise.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User registerUser(String name, String password, String email){
        return userRepository.save(new User(name, password, email));
    }

    public boolean login(LoginForm loginForm) {
        User user = userRepository.findByEmail(loginForm.getEmail());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return user != null && !loginForm.getPassword().equals(passwordEncoder.encode(user.getPassword()));
    }
}

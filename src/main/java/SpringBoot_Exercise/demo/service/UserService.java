package SpringBoot_Exercise.demo.service;

import SpringBoot_Exercise.demo.domain.SiteUser;
import SpringBoot_Exercise.demo.domain.dto.LoginForm;
import SpringBoot_Exercise.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public SiteUser registerUser(String name, String password, String email){
        return userRepository.save(new SiteUser(name, password, email));
    }

    public boolean login(LoginForm loginForm) {
        Optional<SiteUser> user = userRepository.findByEmail(loginForm.getEmail());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return user.isPresent() && !loginForm.getPassword().equals(passwordEncoder.encode(user.get().getPassword()));
    }
}

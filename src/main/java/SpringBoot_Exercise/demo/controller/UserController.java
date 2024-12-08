package SpringBoot_Exercise.demo.controller;

import SpringBoot_Exercise.demo.domain.dto.LoginForm;
import SpringBoot_Exercise.demo.domain.dto.UserDto;
import SpringBoot_Exercise.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    @GetMapping("login")
    public String login(Model model) {
        if (!model.containsAttribute("loginForm")) {
            model.addAttribute("loginForm", new LoginForm());
        }
        return "user_login";
    }

    @PostMapping("login")
    public String login(@Valid @ModelAttribute("loginForm") LoginForm loginForm,
                        BindingResult bindingResult,
                        Model model) {
        if(bindingResult.hasErrors() || !userService.login(loginForm)) {
            model.addAttribute("loginError", true);
            model.addAttribute("loginForm", loginForm);
            return "user_login";
        }
        // 만약 로그인에 성공했다면 세션을 만들어야 하지 않을까?

        return "redirect:/question/list";
    }

    @GetMapping("/signUp")
    public String signUp(Model model){
        model.addAttribute("user", new UserDto());
        return "user_signUp";
    }

    @PostMapping("/signUp")
    public String signUpSubmit(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "user_signUp";
        }

        try {
            userService.registerUser(userDto.getName(),
                    passwordEncoder.encode(userDto.getPassword()),
                    userDto.getEmail());
        }catch(DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "user_signUp";
        }

        return "redirect:/question/list";
    }
}

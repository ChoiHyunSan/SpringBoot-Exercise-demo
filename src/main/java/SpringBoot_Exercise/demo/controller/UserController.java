package SpringBoot_Exercise.demo.controller;

import SpringBoot_Exercise.demo.domain.dto.LoginForm;
import SpringBoot_Exercise.demo.domain.dto.UserDto;
import SpringBoot_Exercise.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("login")
    public String login(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "user_login";
    }

    @PostMapping("login")
    public String login(@Valid @ModelAttribute("loginForm") LoginForm loginForm,
                        BindingResult bindingResult,
                        Model model) {
        if(bindingResult.hasErrors() || !userService.login(loginForm)) {
            model.addAttribute("loginError", true);
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
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userService.registerUser(userDto.getName(),
                passwordEncoder.encode(userDto.getPassword()),
                userDto.getEmail());
        return "redirect:/question/list";
    }

    public UserController(UserService userService) {
        this.userService = userService;
    }

    private final UserService userService;
}

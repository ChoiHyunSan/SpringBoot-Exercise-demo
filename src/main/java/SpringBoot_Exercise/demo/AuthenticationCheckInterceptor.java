package SpringBoot_Exercise.demo;

import SpringBoot_Exercise.demo.service.CustomUserDetails;
import SpringBoot_Exercise.demo.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
public class AuthenticationCheckInterceptor implements HandlerInterceptor {

    private final UserService userService;

    public AuthenticationCheckInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 로그인, 회원가입 등 인증이 필요없는 페이지는 체크하지 않음
        if (request.getRequestURI().startsWith("/user/")) {
            return true;
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 이미 인증된 상태일 때만 체크
        if (authentication != null && authentication.isAuthenticated()
                && !authentication.getPrincipal().equals("anonymousUser")) {
            try {
                CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
                // DB에서 해당 유저가 존재하는지 확인
                if (userService.findByEmail(userDetails.getUsername()).isEmpty()) {
                    SecurityContextHolder.clearContext();
                }
            } catch (Exception e) {
                // 에러 발생시 아무것도 하지 않음
            }
        }
        return true;
    }
}
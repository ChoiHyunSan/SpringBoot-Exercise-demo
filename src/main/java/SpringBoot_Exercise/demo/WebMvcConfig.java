package SpringBoot_Exercise.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final AuthenticationCheckInterceptor authenticationCheckInterceptor;

    public WebMvcConfig(AuthenticationCheckInterceptor authenticationCheckInterceptor) {
        this.authenticationCheckInterceptor = authenticationCheckInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationCheckInterceptor)
                .addPathPatterns("/question/**", "/answer/**")  // 특정 경로만 체크
                .excludePathPatterns("/css/**", "/js/**");      // 정적 리소스 제외
    }
}
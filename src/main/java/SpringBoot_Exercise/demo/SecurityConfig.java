package SpringBoot_Exercise.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers(new AntPathRequestMatcher("/")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/user/login")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/user/signUp")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/css/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/js/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/question/list")).permitAll()  // 추가
                                .requestMatchers(new AntPathRequestMatcher("/question/detail/**")).permitAll()  // 추가// 질문 작성, 수정, 삭제 등은 인증 필요
                                .requestMatchers(new AntPathRequestMatcher("/question/create")).authenticated()
                                .requestMatchers(new AntPathRequestMatcher("/question/modify/**")).authenticated()
                                .requestMatchers(new AntPathRequestMatcher("/question/delete/**")).authenticated() // 답변 관련 작업은 인증 필요
                                .requestMatchers(new AntPathRequestMatcher("/answer/**")).authenticated()
                                .anyRequest().permitAll()  // 기본적으로 모든 요청 허용으로 변경
                )
                .formLogin((formLogin) -> formLogin
                        .loginPage("/user/login")
                        .loginProcessingUrl("/user/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/user/login?error=true")
                )
                .logout((logout) -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                )
                .sessionManagement((sessionManagement) -> sessionManagement
                        .maximumSessions(1)
                        .expiredUrl("/user/login")
                )
                .csrf(csrf -> csrf.disable())
        ;
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
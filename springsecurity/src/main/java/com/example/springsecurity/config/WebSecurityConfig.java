package com.example.springsecurity.config;

import com.example.springsecurity.security.CustomSecurityFilter;
import com.example.springsecurity.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity // 스프링 Security 지원을 가능하게 하는 애너테이션
public class WebSecurityConfig { // 스프링 시큐리티를 사용하기 위해 스프링 시큐리티에 필요한 bean을 추가해주는 config 클래스 파일을 추가

    // sprincSEcurity 5.6 부터는 antMatchers 대신 requestMatchers를 사용해야 하고, authorizeRequests는 authorizHttpRequests로 대체
    // csrf() 설정도 최신 방식으로 변경됨

    private final UserDetailsServiceImpl userDetailsService;


    @Bean // 비밀번호 암호화 기능 등록
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // h2-console 사용 및 resources 접근 허용 설정
        return (web) -> web.ignoring()
                .requestMatchers(PathRequest.toH2Console())
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // CSRF 설정
//        http.csrf().disable();
        http.csrf(csrf -> csrf.disable());

        /*
        http.authorizeRequests(authorize -> authorize
                .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/css/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/js/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/images/**")).permitAll()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .anyRequest().authenticated());
        */

        http.authorizeRequests().requestMatchers("/api/user/**").permitAll()
                .anyRequest().authenticated();


        // Custom 로그인 페이지 사용
        http.formLogin(form -> form
                .loginPage("/api/user/login-page")
                .permitAll()
        );

        // Custom Filter 등록하기
        http.addFilterBefore(new CustomSecurityFilter(userDetailsService, passwordEncoder()));

        return http.build();
    }

}

package com.jupiter.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .httpBasic().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/api/login", "/api/logout", "/api/join",
                        "/api/totalCount", "/error").permitAll()
                .antMatchers("/api/uploadFiles").hasRole("USER")
                .anyRequest().permitAll() //인증필요
                .and()
                .logout(withDefaults());
        http.formLogin().loginProcessingUrl("/login");


        return http.build();
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//
//        registry.addInterceptor(new LoginCheckInterceptor())
//                .order(1)
//                .addPathPatterns("/**") //모든 경로에 필터 적용
//                .excludePathPatterns("/", "/api/login", "/api/logout", "/api/join",
//                        "/api/totalCount", "/error", "/api/prediction"); //필터 적용하지않을 경로
//    }
}

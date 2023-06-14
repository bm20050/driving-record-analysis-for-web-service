package com.jupiter.springboot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jupiter.springboot.config.auth.CustomUsernamePasswordAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.*;

//@Configuration
//@EnableWebSecurity

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
        http.formLogin().disable();


        return http.build();
    }

    @Bean
    public CustomUsernamePasswordAuthenticationFilter getAuthenticationFilter() {

        CustomUsernamePasswordAuthenticationFilter authFilter = new CustomUsernamePasswordAuthenticationFilter(new ObjectMapper());

        try {
            authFilter.setFilterProcessesUrl("/login");
            authFilter.setAuthenticationManager((AuthenticationManager) getAuthenticationFilter());
            authFilter.setUsernameParameter("username");
            authFilter.setPasswordParameter("password");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authFilter;
    }
}






package com.jupiter.springboot.config.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jupiter.springboot.dto.MemberLoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.MimeTypeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper;

    public CustomUsernamePasswordAuthenticationFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        UsernamePasswordAuthenticationToken authenticationToken = null;

        if(request.getContentType().equals(MimeTypeUtils.APPLICATION_JSON_VALUE)) {

            try{
                // ObjectMapper를 이용해서 JSON 데이터를 dto에 저장 후 dto의 데이터를 이용
                MemberLoginDto loginDto = objectMapper.readValue(request.getReader().lines().collect(Collectors.joining()), MemberLoginDto.class);
                authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getUserid(), loginDto.getPassword());
            } catch(IOException e) {
                e.printStackTrace();
                throw new AuthenticationServiceException("Json Parsing Error");
            }
        }

        this.setDetails(request, authenticationToken);

        return this.getAuthenticationManager().authenticate(authenticationToken);

    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return this.getAuthenticationManager();
    }
}

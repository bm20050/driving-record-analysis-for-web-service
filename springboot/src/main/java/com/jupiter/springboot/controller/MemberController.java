package com.jupiter.springboot.controller;

import com.jupiter.springboot.config.auth.PrincipalDetails;
import com.jupiter.springboot.dto.MemberJoinDto;
import com.jupiter.springboot.dto.MemberLoginDto;
import com.jupiter.springboot.dto.MemberLoginRespDto;
import com.jupiter.springboot.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @GetMapping("/authTest")
    public String authTest(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        System.out.println(principalDetails);
        return principalDetails.toString();
    }

    @PostMapping("/api/join")
    public ResponseEntity<Object> join (@Validated @RequestBody MemberJoinDto params){
        memberService.createMember(params);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @PostMapping("/api/login")
    public ResponseEntity<MemberLoginRespDto> login(@Validated @RequestBody MemberLoginDto params, HttpServletRequest request){
        return ResponseEntity.ok().body(memberService.login(params, request));
    }

    @PostMapping("/api/logout")
    public void logout(HttpServletRequest request) {
        memberService.logout(request);
    }
}

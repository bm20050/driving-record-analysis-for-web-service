package com.jupiter.springboot.controller;

import com.jupiter.springboot.dto.MemberJoinDto;
import com.jupiter.springboot.dto.MemberLoginDto;
import com.jupiter.springboot.dto.MemberLoginRespDto;
import com.jupiter.springboot.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/api/join")
    public Long join (@RequestBody MemberJoinDto params){
        return memberService.createMember(params);
    }

    @PostMapping("/api/login")
    public ResponseEntity<MemberLoginRespDto> login(@RequestBody MemberLoginDto params, HttpServletRequest request){
        return ResponseEntity.ok().body(memberService.login(params, request));
    }

    @PostMapping("/api/logout")
    public void logout(HttpServletRequest request) {
        memberService.logout(request);
    }
}

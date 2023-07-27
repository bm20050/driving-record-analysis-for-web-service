package com.jupiter.springboot.controller;

import com.jupiter.springboot.dto.MemberJoinDto;
import com.jupiter.springboot.dto.MemberLoginDto;
import com.jupiter.springboot.dto.MemberLoginRespDto;
import com.jupiter.springboot.dto.MemberUpdateDto;
import com.jupiter.springboot.service.MemberService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

//    @GetMapping("/authTest")
//    public String authTest(@AuthenticationPrincipal PrincipalDetails principalDetails) {
//        System.out.println(principalDetails);
//        return principalDetails.getUsername();
//    }

    @ApiOperation(value = "멤버정보 조회", notes = "session에 저장 된 로그인 사용자정보 응답", response = MemberLoginRespDto.class)
    @PostMapping("/api/user/getuser")
    public ResponseEntity<MemberLoginRespDto> getUser (HttpServletRequest request){
        return ResponseEntity.ok().body(memberService.getUser(request));
    }

    @ApiOperation(value = "회원가입", response = HttpStatus.class)
    @PostMapping("/api/user/join")
    public ResponseEntity<HttpStatus> join (@Valid @RequestBody MemberJoinDto params){

        memberService.createMember(params);

        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @ApiOperation(value = "로그인", response = MemberLoginRespDto.class)
    @PostMapping("/api/user/login")
    public ResponseEntity<MemberLoginRespDto> login(@RequestBody MemberLoginDto params, HttpServletRequest request){
        return ResponseEntity.ok().body(memberService.login(params, request));
    }

    @ApiOperation(value = "로그아웃")
    @PostMapping("/api/user/logout")
    public void logout(HttpServletRequest request) {
        memberService.logout(request);
    }

    @ApiOperation(value = "회원정보 수정", notes = "이름, 이메일주소, 비밀번호 수정", response = MemberLoginRespDto.class)
    @PutMapping("/api/user/update")
    public ResponseEntity<MemberLoginRespDto> update(@Valid @RequestBody MemberUpdateDto params){
        return ResponseEntity.ok().body(memberService.update(params));
    }

}

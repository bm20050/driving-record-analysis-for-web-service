package com.jupiter.springboot.service;

import com.jupiter.springboot.domain.Member;
import com.jupiter.springboot.dto.MemberJoinDto;
import com.jupiter.springboot.dto.MemberLoginDto;
import com.jupiter.springboot.dto.MemberLoginRespDto;
import com.jupiter.springboot.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void createMember(MemberJoinDto params) {

        String rawPassword = params.getPassword();
        String encodingPassword = bCryptPasswordEncoder.encode(rawPassword);

        List<Member> findMembers = memberRepository.findByUserid(params.getUserid()).stream().toList();


        if(!findMembers.isEmpty())
            throw new RuntimeException("이미 존재하는 회원");

        memberRepository.save(new Member(params.getUserid(), params.getUsername(),
                params.getEmail(), encodingPassword, "ROLE_USER"));
    }

    public MemberLoginRespDto login(MemberLoginDto params, HttpServletRequest request){

        Optional<Member> findMember = memberRepository.findByUsername(params.getUsername());

        if(findMember.isPresent()) {
            if(bCryptPasswordEncoder.matches(params.getPassword(), findMember.get().getPassword())){

//                //로그인 성공 시 쿠키에 JSESSIONID 저장
                MemberLoginRespDto loginMember = new MemberLoginRespDto(findMember.get().getUserid(), findMember.get().getUserid(), findMember.get().getEmail());
                HttpSession session = request.getSession();
                session.setAttribute("loginMember", loginMember);

                return new MemberLoginRespDto(findMember.get().getUserid(), findMember.get().getUsername(), findMember.get().getEmail());

            }
            else throw new RuntimeException("비밀번호 오류");
        } else throw new RuntimeException("아이디 없음");
    }

    public void logout(HttpServletRequest request){

        HttpSession session = request.getSession(false);

        if(session!=null)
            session.invalidate();
    }

}

package com.jupiter.springboot.service;

import com.jupiter.springboot.domain.Member;
import com.jupiter.springboot.dto.MemberJoinDto;
import com.jupiter.springboot.dto.MemberLoginDto;
import com.jupiter.springboot.dto.MemberLoginRespDto;
import com.jupiter.springboot.dto.MemberUpdateDto;
import com.jupiter.springboot.exception.DupUserException;
import com.jupiter.springboot.exception.NoUserException;
import com.jupiter.springboot.exception.WrongPasswordException;
import com.jupiter.springboot.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Validated
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void createMember(@Valid MemberJoinDto params) {

        String rawPassword = params.getPassword();
        String encodingPassword = bCryptPasswordEncoder.encode(rawPassword);

        List<Member> findMembers = memberRepository.findByUserid(params.getUserid()).stream().toList();
        
        if(!findMembers.isEmpty())
            throw new DupUserException("아이디 중복");

        memberRepository.save(new Member(params.getUserid(), params.getUsername(),
                params.getEmail(), encodingPassword, "ROLE_USER"));
    }

    public MemberLoginRespDto login(MemberLoginDto params, HttpServletRequest request){

        Optional<Member> findMember = memberRepository.findByUserid(params.getUserid());

        if(findMember.isPresent()) {
            if(bCryptPasswordEncoder.matches(params.getPassword(), findMember.get().getPassword())){

//                //로그인 성공 시 쿠키에 JSESSIONID 저장
                MemberLoginRespDto loginMember = new MemberLoginRespDto(findMember.get().getUserid(), findMember.get().getUserid(), findMember.get().getEmail());
                HttpSession session = request.getSession();
                session.setAttribute("loginMember", loginMember.getUserid());

                return new MemberLoginRespDto(findMember.get().getUserid(), findMember.get().getUsername(), findMember.get().getEmail());

            }
            else throw new WrongPasswordException("비밀번호 오류");
        } else throw new NoUserException("사용자 없음");
    }

    public MemberLoginRespDto getUser(HttpServletRequest request){

        HttpSession session = request.getSession(false);
        String loginMemberId = session.getAttribute("loginMember").toString();
        Optional<Member> findMember = memberRepository.findByUserid(loginMemberId);

        if(findMember.isPresent())
            return new MemberLoginRespDto(findMember.get().getUserid(), findMember.get().getUsername(), findMember.get().getEmail());
        else throw new NoUserException("사용자 없음");
    }

    public void logout(HttpServletRequest request){

        HttpSession session = request.getSession(false);

        if(session!=null)
            session.invalidate();
    }

    public MemberLoginRespDto update(@Valid MemberUpdateDto params){

        String rawPassword = params.getPassword();
        String encodingPassword = bCryptPasswordEncoder.encode(rawPassword);

        Optional<Member> findMember = memberRepository.findByUserid(params.getUserid());

        if(findMember.isPresent()){

            Member member = findMember.get();
            member.setPassword(encodingPassword);
            Optional.ofNullable(params.getUsername()).ifPresent(member::setUsername);
            Optional.ofNullable(params.getEmail()).ifPresent(member::setEmail);

            memberRepository.save(member);

            return new MemberLoginRespDto(member.getUserid(), member.getUsername(), member.getEmail());

        } else throw new NoUserException("사용자 없음");

    }

}

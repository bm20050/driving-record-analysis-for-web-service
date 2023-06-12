package com.jupiter.springboot.service;

import com.jupiter.springboot.domain.Member;
import com.jupiter.springboot.dto.MemberJoinDto;
import com.jupiter.springboot.dto.MemberLoginDto;
import com.jupiter.springboot.dto.MemberLoginRespDto;
import com.jupiter.springboot.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long createMember(MemberJoinDto params) {
//        String rawPassword = params.getPassword();
//        String encodingPassword = bCryptPasswordEncoder.encode(rawPassword);

        //멤버 중복 체크, 컬럼에 유니크 걸어놨는데 어떻게 되지?
//        List<Member> members = memberRepository.findAll();
//        List<Member> collect = members.stream().filter(m -> m.getUserid().equals(params.getUserid())).collect(Collectors.toList());
//
//        if(collect.size()!=0)
//            throw new RuntimeException("아이디 중복");

        memberRepository.save(new Member(params.getUserid(), params.getUsername(),
                params.getEmail(), params.getPassword(), "ROLE_USER"));

        return memberRepository.findByUserid(params.getUserid()).get().getId();

    }

    public MemberLoginRespDto login(MemberLoginDto params, HttpServletRequest request){

        Optional<Member> findMember = memberRepository.findByUserid(params.getUserid());

        if(findMember.isPresent()) {
            if(findMember.get().getPassword().equals(params.getPassword())){
                Member member = findMember.get();

                //로그인 성공 시 세션에 JSESSIONID 보관 (<-회원정보 보관)
                MemberLoginRespDto loginMember = new MemberLoginRespDto(member.getUserid(), member.getUsername(), member.getEmail());
                HttpSession session = request.getSession();
                session.setAttribute("loginMember", loginMember);

                return loginMember;
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

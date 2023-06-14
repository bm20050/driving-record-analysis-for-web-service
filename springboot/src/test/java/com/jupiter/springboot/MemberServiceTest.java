package com.jupiter.springboot;

import com.jupiter.springboot.domain.Member;
import com.jupiter.springboot.dto.MemberUpdateDto;
import com.jupiter.springboot.persistence.MemberRepository;
import com.jupiter.springboot.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;


    @Test
    public void update() throws Exception {
        //given
        MemberUpdateDto param = new MemberUpdateDto("user1", "test1", "1234", "aaa@abc.com");
        memberService.update(param);

        Member updateMember = memberRepository.findByUserid("user1").get();
        //when
        System.out.println(updateMember.getUsername());
        //then
    }

//    @Test
//    public void updateExceptEmail() throws Exception {
//        //given
//        MemberUpdateDto param = new MemberUpdateDto("user1", "test1", "1234", "aaa@abc.com");
//        memberService.update(param);
//
//        Member updateMember = memberRepository.findByUserid("user1").get();
//        //when
//        System.out.println(updateMember.toString());
//        //then
//    }
}

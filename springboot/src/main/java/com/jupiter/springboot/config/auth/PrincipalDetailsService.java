package com.jupiter.springboot.config.auth;

import com.jupiter.springboot.domain.Member;
import com.jupiter.springboot.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("userid: "+ username);

        Member memberEntity = memberRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("Member Not Found"));

        if(memberEntity!=null) {
            log.info("memberEntity: "+ memberEntity.toString());
            return new PrincipalDetails(memberEntity);
        }

        return null;
    }
}

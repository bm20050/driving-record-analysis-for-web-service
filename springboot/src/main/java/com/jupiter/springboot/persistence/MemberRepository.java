package com.jupiter.springboot.persistence;

import com.jupiter.springboot.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUsername(String userid);
}

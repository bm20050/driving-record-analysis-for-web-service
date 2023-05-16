package com.jupiter.springboot.persistence;

import com.jupiter.springboot.domain.Reqlog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReqlogRepository extends JpaRepository<Reqlog, Long> {
}

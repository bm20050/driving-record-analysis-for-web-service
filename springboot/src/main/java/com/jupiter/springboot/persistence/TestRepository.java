package com.jupiter.springboot.persistence;

import com.jupiter.springboot.domain.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {

}

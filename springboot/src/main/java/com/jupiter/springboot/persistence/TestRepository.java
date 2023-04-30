package com.jupiter.springboot.persistence;

import com.jupiter.springboot.domain.Test;
import org.springframework.data.repository.CrudRepository;

public interface TestRepository extends CrudRepository<Test, Long> {
}

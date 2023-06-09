package com.jupiter.springboot.persistence;

import com.jupiter.springboot.domain.UploadList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadListRepository extends JpaRepository<UploadList, Long> {


}

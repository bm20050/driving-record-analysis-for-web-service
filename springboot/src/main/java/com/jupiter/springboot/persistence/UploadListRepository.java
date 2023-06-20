package com.jupiter.springboot.persistence;

import com.jupiter.springboot.domain.Member;
import com.jupiter.springboot.domain.UploadList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UploadListRepository extends JpaRepository<UploadList, Long> {

    @Query("select u, m from UploadList u join fetch u.member m where m.id = :memberId")
    List<UploadList> findWithMember(Long memberId);

}

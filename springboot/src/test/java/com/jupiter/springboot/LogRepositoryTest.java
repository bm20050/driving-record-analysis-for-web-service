package com.jupiter.springboot;


import com.jupiter.springboot.domain.Reqlog;
import com.jupiter.springboot.persistence.ReqlogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
public class LogRepositoryTest {

    @Autowired
    ReqlogRepository reqlogRepository;

    @Commit
    @Test
    public void save() {
        Reqlog log = new Reqlog();
        log.setDrivingDate("221201");
        log.setTrip("02");
        log.setPlate("부산70자1854");
        log.setReqMenu("map");

        reqlogRepository.save(log);

        Reqlog result = reqlogRepository.findById(log.getSeq()).get();

        assertThat(log).isEqualTo(result);

    }
}

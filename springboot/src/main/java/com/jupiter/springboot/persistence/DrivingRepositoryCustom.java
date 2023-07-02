package com.jupiter.springboot.persistence;

import com.jupiter.springboot.domain.Driving;
import com.jupiter.springboot.dto.ReqParams;

import java.util.List;

public interface DrivingRepositoryCustom {

    List<Driving> searchDriving(ReqParams reqParams);
}

package com.jupiter.springboot.controller;

import com.jupiter.springboot.domain.Driving;
import com.jupiter.springboot.dto.PredReqParams;
import com.jupiter.springboot.dto.ReqParams;
import com.jupiter.springboot.service.DrivingService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DrivingController {

    private final DrivingService drivingService;

    @ApiOperation(value = "위험운전 조회", notes = "버스별(전체/개별), 위험별(전체/개별) 데이터 조회", response = Driving.class)
    @PostMapping("/api/totalCount")
    public ResponseEntity<List<Driving>> totalCount(@RequestBody ReqParams params) {
        return ResponseEntity.ok().body(drivingService.totalCount(params));
    }

    @ApiOperation(value = "예측모델을 통한 위험운전 조회", response = String.class)
    @PostMapping("/api/prediction")
    public ResponseEntity<?> prediction(@RequestBody PredReqParams params){
        return ResponseEntity.ok().body(drivingService.transferPred(params));
    }

}

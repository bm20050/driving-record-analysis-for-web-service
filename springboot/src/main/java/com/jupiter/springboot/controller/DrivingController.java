package com.jupiter.springboot.controller;

import com.jupiter.springboot.domain.Driving;
import com.jupiter.springboot.dto.PredReqParams;
import com.jupiter.springboot.dto.ReqParams;
import com.jupiter.springboot.service.DrivingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequiredArgsConstructor
public class DrivingController {

    private final DrivingService drivingService;

    @PostMapping("/api/totalCount")
    public ResponseEntity<List<Driving>> totalCount(@RequestBody ReqParams params) {
        System.out.println(params.toString());
        return ResponseEntity.ok().body(drivingService.totalCount(params));
    }

    @PostMapping("/api/prediction")
    public ResponseEntity<?> login(@RequestBody PredReqParams params){
        return ResponseEntity.ok().body(drivingService.transferPred(params));
    }

}

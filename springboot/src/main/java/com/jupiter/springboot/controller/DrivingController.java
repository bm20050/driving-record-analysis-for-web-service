package com.jupiter.springboot.controller;

import com.jupiter.springboot.domain.Driving;
import com.jupiter.springboot.dto.ReqParams;
import com.jupiter.springboot.service.DrivingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class DrivingController {

    DrivingService drivingService;

    @Autowired
    public DrivingController(DrivingService drivingService) {
        this.drivingService = drivingService;
    }

    @GetMapping("/getData")
    public List<Driving> getList(){
        return drivingService.getList();
    }

    @GetMapping("/api/yesterday")
    public List<Driving> yesterday() {
        return drivingService.yesterday();
    }

    @ResponseBody
    @PostMapping("/api/totalCount")
    public List<Driving> totalCount(@RequestBody ReqParams params) {
        System.out.println(params.toString());
        return drivingService.totalCount(params);
    }

}

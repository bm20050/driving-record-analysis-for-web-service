package com.jupiter.springboot.controller;

import com.jupiter.springboot.domain.Driving;
import com.jupiter.springboot.domain.Reqlog;
import com.jupiter.springboot.service.DrivingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PostMapping("/api/map_req")
    public void mapReq(@RequestParam Reqlog req) {

    }

    @GetMapping("/api/map_resp")
    public List<Driving> mapData(){
        return null;
    }
}

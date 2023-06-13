package com.jupiter.springboot.controller;

import com.jupiter.springboot.domain.Test;
import com.jupiter.springboot.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class TestController {

    @Autowired
    TestService testService;

    @GetMapping("/api/test")
    public ResponseEntity<?> test() {

        System.out.println("controller 진입지점");
        
        return testService.test();
    }

    @GetMapping("/api/getData")
    public List<Test> getData() {

        System.out.println("controller : 리액트로 데이터 보내주기");

        return testService.getData();
    }

//    @ResponseBody
//    @PostMapping("/api/sendData")
//    public Message getMessage(@RequestBody Message params) {
//
//        System.out.println("controller : 리액트에서 데이터 받아오기");
//        System.out.println(params.getDate());
//        System.out.println(params.getTime());
//
//        return params;
//    }


}

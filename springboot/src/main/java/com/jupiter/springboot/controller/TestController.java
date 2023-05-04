package com.jupiter.springboot.controller;

import com.jupiter.springboot.domain.Test;
import com.jupiter.springboot.domain.Test2;
import com.jupiter.springboot.persistence.Test2Repository;
import com.jupiter.springboot.persistence.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class TestController {


    @Autowired
    TestRepository testRepo;

    @Autowired
    Test2Repository test2Repo;

    @ResponseBody
    @GetMapping("/api/send/{userName}")
    public String getMessage(@PathVariable String userName) {
        System.out.println(userName);
        return userName;
    }

    @ResponseBody
    @PostMapping("/api/send") //
    public Test getMessage(@RequestBody Test params) {
        System.out.println(params.getUserName());
        System.out.println(params.getPassword());

        return params;
    }

    @ResponseBody
    @PostMapping("/api/send1") //
    public void insertMessage(@RequestBody Test params) {
        System.out.println(params.getUserName());
        System.out.println(params.getPassword());

        testRepo.save(params);
    }

    @GetMapping("/api/getData")
    public List<Test2> getData() {

        return (List<Test2>) test2Repo.findAll();
    }
}

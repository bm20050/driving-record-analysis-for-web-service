package com.jupiter.springboot.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jupiter.springboot.domain.Test2;
import com.jupiter.springboot.persistence.Test2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;


@RestController
public class Test2Controller {

    @Autowired
    Test2Repository test2Repo;

    @GetMapping ("/api/test")
    public ResponseEntity<?> test(){

//        StringBuilder sb = new StringBuilder();

        String urlStr = "http://localhost:5000/get"; //flask return 주소

        URL url;

        ObjectMapper objMapper = new ObjectMapper();
        objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); //필요한 데이터만 파싱
        List<Test2> testList = null;

        try{
            url = new URL(urlStr);

            //Http, Https
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));

            testList = objMapper.readValue(br.readLine(), new TypeReference<>() {});
            test2Repo.saveAll(testList);

            urlConnection.disconnect();


        }catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(testList, HttpStatus.OK);
    }

}

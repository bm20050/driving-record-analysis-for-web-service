package com.jupiter.springboot.controller;

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
        Test2 test = null;

        try{
            url = new URL(urlStr);

            //Http, Https
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

            test = objMapper.readValue(br.readLine(),Test2.class);

            System.out.println(test);

            test2Repo.save(test);

            urlConnection.disconnect();


        }catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(test.toString(), HttpStatus.OK);
    }

}

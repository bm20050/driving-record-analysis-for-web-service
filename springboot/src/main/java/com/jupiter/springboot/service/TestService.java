package com.jupiter.springboot.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jupiter.springboot.domain.Test;
import com.jupiter.springboot.persistence.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class TestService {

    @Autowired
    TestRepository testRepo;

    public ResponseEntity<?> test(){

        String urlStr = "http://localhost:5000/get"; //flask return 주소

        URL url;

        ObjectMapper objMapper = new ObjectMapper();
//        objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); //필요한 데이터만 파싱
        List<Test> testList = null;

        try{
            url = new URL(urlStr);

            //Http, Https
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));

            testList = objMapper.readValue(br.readLine(), new TypeReference<>() {});
            testRepo.saveAll(testList);

            urlConnection.disconnect();


        }catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(testList, HttpStatus.OK);
    }


    public List<Test> getData() {
        return testRepo.findAll();
    }
}

package com.jupiter.springboot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class DrivingRecordController {

    @GetMapping("/api/work")
    public ResponseEntity<?> work(){

        StringBuilder sb = new StringBuilder();

        String urlStr = "http://localhost:5000/get"; //flask return 주소

        URL url;

        try{
            url = new URL(urlStr);

            //Http, Https
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

            String returnLine;

            while ((returnLine = br.readLine()) != null) {
                sb.append(returnLine).append("\n\r");
            }

            urlConnection.disconnect();


        }catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(sb.toString(), HttpStatus.OK);
    }
}

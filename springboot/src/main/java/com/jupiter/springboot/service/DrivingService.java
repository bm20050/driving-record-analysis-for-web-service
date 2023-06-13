package com.jupiter.springboot.service;

import com.jupiter.springboot.domain.Driving;
import com.jupiter.springboot.dto.PredReqParams;
import com.jupiter.springboot.dto.ReqParams;
import com.jupiter.springboot.persistence.DrivingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DrivingService {

    private final DrivingRepository drivingRepository;

    public List<Driving> getList() {
        return drivingRepository.findAll();
    }

    public List<Driving> yesterday() {

        Calendar today = new GregorianCalendar();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        today.add(Calendar.DATE, -1);
        String yesterday = simpleDateFormat.format(today.getTime());
        System.out.println(yesterday);

        return drivingRepository.findByPlateAndYearAndMonthAndDay("부산70자1854", "22", "12", "02");
    }

    public List<Driving> totalCount(ReqParams params) {

        String year = params.getYear();
        String month = params.getMonth();
        String day = params.getDay();
        String plate = params.getBusNumber();
        String danger = params.getDanger();

        if (plate.contains("total")) {
            if (danger.contains("totalDan"))
                return drivingRepository.findTotal(year, month, day, 1, 1, 1, 1);
            else if (danger.contains("suddenAcc"))
                return drivingRepository.findByYearAndMonthAndDayAndSuddenAcc(year, month, day, 1);
            else if (danger.contains("suddenDeparture"))
                return drivingRepository.findByYearAndMonthAndDayAndSuddenDeparture(year, month, day, 1);
            else if (danger.contains("suddenDrop"))
                return drivingRepository.findByYearAndMonthAndDayAndSuddenDrop(year, month, day, 1);
            else
                return drivingRepository.findByYearAndMonthAndDayAndSuddenStop(year, month, day, 1);
        } else {
            if (danger.contains("totalDan"))
                return drivingRepository.findTotalPlate(year, month, day, 1, 1, 1, 1, plate);
            else if (danger.contains("suddenAcc"))
                return drivingRepository.findByPlateLikeAndYearAndMonthAndDayAndSuddenAcc(plate, year, month, day, 1);
            else if (danger.contains("suddenDeparture"))
                return drivingRepository.findByPlateLikeAndYearAndMonthAndDayAndSuddenDeparture(plate, year, month, day, 1);
            else if (danger.contains("suddenDrop"))
                return drivingRepository.findByPlateLikeAndYearAndMonthAndDayAndSuddenDrop(plate, year, month, day, 1);
            else
                return drivingRepository.findByPlateLikeAndYearAndMonthAndDayAndSuddenStop(plate, year, month, day, 1);

        }
    }


    public String transferPred(PredReqParams params){

        Map<String, PredReqParams> map = new HashMap<>();
        map.put("data", params);

        String flaskUrl = "http://localhost:5000/prediction";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, PredReqParams>> request = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(flaskUrl, request, String.class);

        return response.getBody();

    }
}

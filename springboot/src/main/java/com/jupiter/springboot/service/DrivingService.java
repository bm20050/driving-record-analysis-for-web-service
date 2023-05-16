package com.jupiter.springboot.service;

import com.jupiter.springboot.domain.Driving;
import com.jupiter.springboot.persistence.DrivingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
public class DrivingService {

    DrivingRepository drivingRepository;

    @Autowired
    public DrivingService(DrivingRepository drivingRepository) {
        this.drivingRepository = drivingRepository;
    }

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

}

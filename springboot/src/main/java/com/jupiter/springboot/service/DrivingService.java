package com.jupiter.springboot.service;

import com.jupiter.springboot.domain.Driving;
import com.jupiter.springboot.domain.ReqParams;
import com.jupiter.springboot.persistence.DrivingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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
}

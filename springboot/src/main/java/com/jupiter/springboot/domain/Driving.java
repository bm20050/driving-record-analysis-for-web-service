package com.jupiter.springboot.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Getter
@Entity
public class Driving {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "차량번호")
    private String plate;

    @Column(name = "년")
    private String year;

    @Column(name = "월")
    private String month;

    @Column(name = "일")
    private String day;

    @Column(name = "시")
    private String time;

//    @Column(name = "정보발생일시")
//    private String infoTime;
//
//    @Column(name = "운행회차")
//    private int trip;

    @Column(name = "일일주행거리")
    private int drivingDistance;

//    @Column(name = "주행시간")
//    private int drivingTime;

    @Column(name = "급가속")
    private int suddenAcc;

    @Column(name = "급출발")
    private int suddenDeparture;

    @Column(name = "급감속")
    private int suddenDrop;

    @Column(name = "급정지")
    private int suddenStop;

//    @Column(name = "이상여부")
//    private String abnormal;

    @Column(name = "GPS_X")
    private String gpsX;

    @Column(name = "GPS_Y")
    private String gpsY;

}


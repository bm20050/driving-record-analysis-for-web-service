package com.jupiter.springboot.domain;

import lombok.Getter;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Getter
@Entity
@Table(indexes = {
        @Index(columnList = "차량번호"),
        @Index(columnList = "년"),
        @Index(columnList = "월"),
        @Index(columnList = "일")
})
@Transactional(readOnly = true)
public class Driving {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "차량번호")
    private String plate;

    @Column(name = "년")
    private int year;

    @Column(name = "월")
    private int month;

    @Column(name = "일")
    private int day;

    @Column(name = "시")
    private int time;

    @Column(name = "시분초")
    private String hms;

    @Column(name = "차량속도")
    private int velocity;

    @Column(name = "가속도")
    private int acceleration;

    @Column(name = "RPM")
    private int rpm;

    @Column(name = "급가속")
    private int suddenAcc;

    @Column(name = "급출발")
    private int suddenDeparture;

    @Column(name = "급감속")
    private int suddenDrop;

    @Column(name = "급정지")
    private int suddenStop;

    @Column(name = "GPS_X")
    private String gpsX;

    @Column(name = "GPS_Y")
    private String gpsY;

}


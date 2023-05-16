package com.jupiter.springboot.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@Setter
@Getter
@Entity
public class Reqlog {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @CreationTimestamp
    @Column(name="요청일시")
    private LocalDateTime reqDate;

    @Column(name="차량번호")
    private String plate;

    @Column(name="운행일시")
    private String drivingDate;

    @Column(name="운행회차")
    private String trip;

    @Column(name="요청메뉴")
    private String reqMenu;

    @Column(name="응답메시지")
    private String message;

}

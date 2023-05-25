package com.jupiter.springboot.domain;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ReqParams {

    private String year;
    private String month;
    private String day;
    private String busNumber;
    private String danger;
}

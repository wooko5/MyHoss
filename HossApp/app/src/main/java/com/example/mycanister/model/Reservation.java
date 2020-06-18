package com.example.mycanister.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Reservation {

    private String user_id;
    private String hospital_id;
    private int reservation_number;
    private String reservation_time;    //예약 시간
    private String reservation_status;  //예약 상태
    private String reservation_estimated_time;  //대기 시간
    private String office_number;               //진료실 번호

}

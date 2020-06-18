package com.example.mycanister.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Hospital implements Serializable {

    private static final long serialVersionUID  = 1L;
    private String hospital_id;
    private String hospital_pw;//
    private String hospital_name;
    private String address;
    private String department;
    private String hours;

}

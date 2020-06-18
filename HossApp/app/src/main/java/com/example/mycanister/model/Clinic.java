package com.example.mycanister.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Clinic {

    private int clinic_number;
    private String user_id;
    private String doctor_id;
    private String clinic_date;
    private String clinic_treatment;
    private String prescription_drugname;
    private String prescription_dosage;
    private String prescription_caution;

}

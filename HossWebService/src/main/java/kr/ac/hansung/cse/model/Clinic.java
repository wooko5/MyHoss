package kr.ac.hansung.cse.model;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Clinic {

    private int clinic_number;
    
    private String user_id;
    
    private String doctor_id;
    
	@NotEmpty(message="The clinic_date must not be null")
    private String clinic_date;

	@NotEmpty(message="The clinic_treatment must not be null")
    private String clinic_treatment;
	
    private String prescription_drugname;
	
    private String prescription_dosage;
	
    private String prescription_caution;
    
}

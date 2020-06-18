package kr.ac.hansung.cse.model;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Doctor {

	@NotEmpty(message="The hospital_id must not be null")
    private String doctor_id;

	@NotEmpty(message="The hospital_id must not be null")
    private String doctor_pw;

	@NotEmpty(message="The hospital_id must not be null")
	private String doctor_name;
	
    private String hospital_id;
    
    private String office_number;
    
}

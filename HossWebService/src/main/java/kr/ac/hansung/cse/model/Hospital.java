package kr.ac.hansung.cse.model;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Hospital {

	@NotEmpty(message="The hospital_id must not be null")
	private String hospital_id;

	@NotEmpty(message="The hospital_pw must not be null")
	private String hospital_pw;

	@NotEmpty(message="The hospital_name must not be null")
	private String hospital_name;

	@NotEmpty(message="The address must not be null")
	private String address;

	@NotEmpty(message="The department must not be null")
	private String department;

	@NotEmpty(message="The hours must not be null")
	private String hours;
	
	private String rejected_time;
}

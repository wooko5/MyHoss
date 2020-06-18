package kr.ac.hansung.cse.model;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Office {

	@NotEmpty(message="The hospital_id must not be null")
	private String hospital_id;
	
	@NotEmpty(message="The office_number must not be null")
	private String office_number;

	@NotEmpty(message="The office_department must not be null")
	private String office_department;
	
	private String waiting_number;
	
}

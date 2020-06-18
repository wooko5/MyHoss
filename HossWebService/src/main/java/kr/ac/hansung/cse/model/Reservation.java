package kr.ac.hansung.cse.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Reservation {
	
	private String user_id;
	
	private String hospital_id;
	
	private int reservation_number;

	private String reservation_time;

	private String reservation_status;

	private String reservation_estimated_time;
	
	private String office_number;
	
}

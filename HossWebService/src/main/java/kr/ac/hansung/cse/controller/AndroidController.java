package kr.ac.hansung.cse.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.hansung.cse.model.Clinic;
import kr.ac.hansung.cse.model.Doctor;
import kr.ac.hansung.cse.model.Hospital;
import kr.ac.hansung.cse.model.Reservation;
import kr.ac.hansung.cse.model.User;
import kr.ac.hansung.cse.service.HossService;

@Controller
@RequestMapping("/android")
public class AndroidController {

	@Autowired
	private HossService hossService;

	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/addUser", produces = "application/json;charset=utf-8", consumes = "application/json;charset=utf-8")
	public JSONObject addUser(@RequestBody JSONObject json) {
		JSONObject jsonMain = new JSONObject();
		if (hossService.checkUserExistence((String) json.get("user_id")) == true) {
			jsonMain.put("message", "중복되는 id 존재");
			return jsonMain;
		}

		User user = new User();
		user.setUser_id((String) json.get("user_id"));
		user.setUser_pw((String) json.get("user_pw"));
		user.setUser_name((String) json.get("user_name"));
		user.setUser_birth((String) json.get("user_birth"));
		user.setUser_Phone((String) json.get("user_Phone"));
		user.setUser_address((String) json.get("user_address"));
		user.setUser_email((String) json.get("user_email"));

		hossService.addUser(user);
		jsonMain.put("message", "중복되는 id 없음");
		return jsonMain;
	}

	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/login", produces = "application/json;charset=utf-8", consumes = "application/json;charset=utf-8")
	public JSONObject login(@RequestBody JSONObject json) {
		JSONObject jsonMain = new JSONObject();
		if (hossService.compareUserIdPw((String) json.get("user_id"), (String) json.get("user_pw")) == false) {
			jsonMain.put("message", "로그인 실패");
			return jsonMain;
		}

		User user = hossService.getUserByUser_id((String) json.get("user_id"));
		jsonMain.put("user_name", user.getUser_name());
		jsonMain.put("user_birth", user.getUser_birth());
		jsonMain.put("user_Phone", user.getUser_Phone());
		jsonMain.put("user_address", user.getUser_address());
		jsonMain.put("user_email", user.getUser_email());

		jsonMain.put("message", "로그인 성공");
		return jsonMain;
	}

	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/getHospitals", produces = "application/json;charset=utf-8")
	public JSONObject getHospitals() {
		JSONObject jsonMain = new JSONObject();

		List<Hospital> hospitals = hossService.getHospitals();

		JSONArray jArray = new JSONArray();
		for (int i = 0; i < hospitals.size(); i++) {
			Hospital hospital = hospitals.get(i);
			JSONObject row = new JSONObject();
			row.put("hospital_id", hospital.getHospital_id());
			row.put("hospital_name", hospital.getHospital_name());
			row.put("address", hospital.getAddress());
			row.put("department", hospital.getDepartment());
			row.put("hours", hospital.getHours());

			jArray.add(i, row);
		}

		jsonMain.put("sendData", jArray);
		return jsonMain;
	}

	@ResponseBody
	@RequestMapping(value = "/addReservation", consumes = "application/json;charset=utf-8")
	public void addReservation(@RequestBody JSONObject json) {

		Reservation reservation = new Reservation();
		reservation.setUser_id((String) json.get("user_id"));
		reservation.setHospital_id((String) json.get("hospital_id"));
		reservation.setReservation_time((String) json.get("reservation_time"));

		Hospital hospital = hossService.getHospitalByHospital_id(reservation.getHospital_id());
		if (hospital.getRejected_time() != null && hospital.getRejected_time().length() != 0) {
			String[] s1 = ((String) json.get("reservation_time")).split(" "); // 예약, s1[0] MM:dd, s1[1] HH:mm

			String rejected_time = hospital.getRejected_time();
			String[] s2 = rejected_time.split(" "); // 예약거부, s2[0] MM:dd, s2[1] HH:mm~HH:mm

			if (s1[0].equals(s2[0])) { // 월, 일이 같을 때만
				String[] s3 = s2[1].split("~");
				String[] s4 = s3[0].split(":");
				String[] s5 = s3[1].split(":");

				// h1:m1~h2:m2(거부 시간)
				int h1 = Integer.parseInt(s4[0]);
				int m1 = Integer.parseInt(s4[1]);
				int h2 = Integer.parseInt(s5[0]);
				int m2 = Integer.parseInt(s5[1]);

				double time1 = h1 + m1 / 60.0;
				double time2 = h2 + m2 / 60.0;

				String[] s6 = s1[1].split(":");

				// h:m(예약 시간)
				int h = Integer.parseInt(s6[0]);
				int m = Integer.parseInt(s6[1]);

				double time = h + m / 60.0;

				if (time1 <= time && time2 >= time) {
					reservation.setReservation_status("거부");
				}
			}
		}
		
//		List<Reservation> reservations = hossService.getWaitingReservationsByHospital_id(reservation.getHospital_id());
//		for(Reservation r: reservations) {
//			String[] s1 = r.getReservation_time().split(" ");
//			if(s1[0].equals(anObject))
//			String[] s2 = s1[1].split(":");
//			s2[0]
//		}

		// System.out.println(reservation);

		hossService.addReservation(reservation);
	}

	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/getReservations", produces = "application/json;charset=utf-8", consumes = "application/json;charset=utf-8")
	public JSONObject getReservations(@RequestBody JSONObject json) {

		JSONObject jsonMain = new JSONObject();
		List<Reservation> reservations = hossService.getWaitingReservationsByUser_id((String) json.get("user_id"));
		JSONArray jArray = new JSONArray();

		for (int i = 0; i < reservations.size(); i++) {
			Reservation reservation = reservations.get(i);
			JSONObject row = new JSONObject();

			Hospital hospital = hossService.getHospitalByHospital_id(reservation.getHospital_id());
			row.put("hospital_name", hospital.getHospital_name());
			row.put("address", hospital.getAddress());
			row.put("department", hospital.getDepartment());
			row.put("hours", hospital.getHours());

			row.put("reservation_number", reservation.getReservation_number());
			row.put("reservation_time", reservation.getReservation_time());
			row.put("reservation_status", reservation.getReservation_status());
			row.put("reservation_estimated_time", reservation.getReservation_estimated_time());
			row.put("office_number", reservation.getOffice_number());

			jArray.add(i, row);
		}

		jsonMain.put("sendData", jArray);
		return jsonMain;
	}

	@ResponseBody
	@RequestMapping(value = "/deleteReservation", consumes = "application/json;charset=utf-8")
	public void deleteReservation(@RequestBody JSONObject json) {
		System.out.println((int) json.get("reservation_number"));
		hossService.deleteReservation(Integer.toString((int) json.get("reservation_number")));
	}

	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/getClinics", produces = "application/json;charset=utf-8", consumes = "application/json;charset=utf-8")
	public JSONObject getClinics(@RequestBody JSONObject json) {
		JSONObject jsonMain = new JSONObject();
		List<Clinic> clinics = hossService.getClinicsByUser_id((String) json.get("user_id"));
		JSONArray jArray = new JSONArray();

		for (int i = 0; i < clinics.size(); i++) {
			Clinic clinic = clinics.get(i);
			JSONObject row = new JSONObject();

			Doctor doctor = hossService.getDoctorByDoctor_id(clinic.getDoctor_id());
			Hospital hospital = hossService.getHospitalByHospital_id(doctor.getHospital_id());
			row.put("hospital_name", hospital.getHospital_name());
			row.put("doctor_name", doctor.getDoctor_name());

			row.put("clinic_date", clinic.getClinic_date());
			row.put("clinic_treatment", clinic.getClinic_treatment());
			row.put("prescription_drugname", clinic.getPrescription_drugname());
			row.put("prescription_dosage", clinic.getPrescription_dosage());
			row.put("prescription_caution", clinic.getPrescription_caution());

			jArray.add(i, row);
		}

		jsonMain.put("sendData", jArray);
		return jsonMain;
	}
}

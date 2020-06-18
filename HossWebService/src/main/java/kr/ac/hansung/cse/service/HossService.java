package kr.ac.hansung.cse.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.cse.dao.HospitalDao;
import kr.ac.hansung.cse.dao.ClinicDao;
import kr.ac.hansung.cse.dao.DoctorDao;
import kr.ac.hansung.cse.dao.OfficeDao;
import kr.ac.hansung.cse.dao.ReservationDao;
import kr.ac.hansung.cse.dao.UserDao;
import kr.ac.hansung.cse.model.Clinic;
import kr.ac.hansung.cse.model.Doctor;
import kr.ac.hansung.cse.model.Hospital;
import kr.ac.hansung.cse.model.Office;
import kr.ac.hansung.cse.model.Reservation;
import kr.ac.hansung.cse.model.User;

@Service
public class HossService {
	
	@Autowired
	private ReservationDao reservationDao;
	
	public List<Reservation> getWaitingReservationsByUser_id(String user_id) {
		return reservationDao.getWaitingReservationsByUser_id(user_id);
	}
	
	public List<Reservation> getWaitingReservationsByHospital_id(String hospital_id) {
		return reservationDao.getWaitingReservationsByHospital_id(hospital_id);
	}
	
	public List<Reservation> getAcceptedReservationsByHospital_idOffice_number(String hospital_id, String office_number) {
		return reservationDao.getAcceptedReservationsByHospital_idOffice_number(hospital_id, office_number);
	}
	
	public Reservation getReservationByReservation_number(String reservation_number) {
		return reservationDao.getReservationByReservation_number(reservation_number);
	}

	public boolean addReservation(Reservation reservation) {
		return reservationDao.addReservation(reservation);
	}
	
	public boolean updateReservation(@Valid Reservation reservation) {
		return reservationDao.updateReservation(reservation);
	}
	
	public boolean deleteReservation(String reservation_number) {
		return reservationDao.deleteReservation(reservation_number);
	}
	
	@Autowired
	private OfficeDao officeDao;
	
	public List<Office> getOfficesByHospital_id(String hospital_id) {
		return officeDao.getOfficesByHospital_id(hospital_id);
	}

	public Office getOfficeByHospital_idOffice_number(String hospital_id, String office_number) {
		return officeDao.getOfficeByHospital_idOffice_number(hospital_id, office_number);
	}
	
	public boolean addOffice(Office office) {
		return officeDao.addOffice(office);
	}

	public boolean deleteOffice(String hospital_id, String office_number) {
		return officeDao.deleteOffice(hospital_id, office_number);
	}

	public boolean updateOffice(@Valid Office office) {
		return officeDao.updateOffice(office);
	}


	@Autowired
	private UserDao userDao;
	
	public boolean addUser(User user) {
		return userDao.addUser(user);
	}
	
	public boolean checkUserExistence(String user_id) {
		return userDao.checkUserExistence(user_id);
	}
	
	public boolean compareUserIdPw(String user_id, String user_pw) {
		return userDao.compareUserIdPw(user_id, user_pw);
	}
	
	public User getUserByUser_id(String user_id) {
		return userDao.getUserByUser_id(user_id);
	}


	@Autowired
	private HospitalDao hospitalDao;

	public Hospital getHospitalByHospital_id(String hospital_id) {
		return hospitalDao.getHospitalByHospital_id(hospital_id);
	}
	
	public List<Hospital> getHospitals() {
		return hospitalDao.getHospitals();
	}
	
	public boolean addHospital(Hospital hospital) {
		return hospitalDao.addHospital(hospital);
	}

	public boolean checkHospitalExistence(String hospital_id) {
		return hospitalDao.checkHospitalExistence(hospital_id);
	}
	
	public boolean updateHospital(@Valid Hospital hospital) {
		return hospitalDao.updateHospital(hospital);
	}

	
	@Autowired
	private DoctorDao doctorDao;
	
	public List<Doctor> getDoctors() {
		return doctorDao.getDoctors();
	}
	
	public List<Doctor> getDoctorsByHospital_id(String hospital_id) {
		return doctorDao.getDoctorsByHospital_id(hospital_id);
	}
	
	public Doctor getDoctorByDoctor_id(String doctor_id) {
		return doctorDao.getDoctorByDoctor_id(doctor_id);
	}
	
	public boolean addDoctor(Doctor doctor) {
		return doctorDao.addDoctor(doctor);
	}
	
	public boolean checkDoctorExistence(String doctor_id) {
		return doctorDao.checkDoctorExistence(doctor_id);
	}
	
	public boolean deleteDoctor(String doctor_id) {
		return doctorDao.deleteDoctor(doctor_id);
	}

	public boolean updateDoctor(@Valid Doctor doctor) {
		return doctorDao.updateDoctor(doctor);
	}

	@Autowired
	private ClinicDao clinicDao;

	public boolean addClinic(Clinic clinic) {
		return clinicDao.addClinic(clinic);
	}
	public Clinic getClinicByClinic_number(String clinic_number) {
		return clinicDao.getClinicByClinic_number(clinic_number);
	}
	
	public List<Clinic> getClinicsByUser_id(String user_id) {
		return clinicDao.getClinicsByUser_id(user_id);
	}
	
	public List<Clinic> getClinicsByDoctor_id(String doctor_id) {
		return clinicDao.getClinicsByDoctor_id(doctor_id);
	}
	
	public boolean updateClinic(@Valid Clinic clinic) {
		return clinicDao.updateClinic(clinic);
	}
	
}

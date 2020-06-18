package kr.ac.hansung.cse.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.ac.hansung.cse.model.Doctor;

@Repository
public class DoctorDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<Doctor> getDoctors() {

		String sqlStatement = "select * from doctor";

		return jdbcTemplate.query(sqlStatement, new RowMapper<Doctor>() {

			@Override
			public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException {
				Doctor doctor = new Doctor();

				doctor.setDoctor_id(rs.getString("doctor_id"));
				doctor.setDoctor_pw(rs.getString("doctor_pw"));
				doctor.setDoctor_name(rs.getString("doctor_name"));
				doctor.setHospital_id(rs.getString("hospital_id"));
				doctor.setOffice_number(rs.getString("office_number"));

				return doctor;
			}
		});

	}

	public List<Doctor> getDoctorsByHospital_id(String hospital_id) {

		String sqlStatement = "select * from doctor where hospital_id=?";

		return jdbcTemplate.query(sqlStatement, new Object[] { hospital_id }, new RowMapper<Doctor>() {

			@Override
			public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException {
				Doctor doctor = new Doctor();

				doctor.setDoctor_id(rs.getString("doctor_id"));
				doctor.setDoctor_pw(rs.getString("doctor_pw"));
				doctor.setDoctor_name(rs.getString("doctor_name"));
				doctor.setHospital_id(rs.getString("hospital_id"));
				doctor.setOffice_number(rs.getString("office_number"));

				return doctor;
			}

		});

	}

	public Doctor getDoctorByDoctor_id(String doctor_id) {

		String sqlStatement = "select * from doctor where doctor_id=?";

		return jdbcTemplate.queryForObject(sqlStatement, new Object[] { doctor_id }, new RowMapper<Doctor>() {

			@Override
			public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException {
				Doctor doctor = new Doctor();

				doctor.setDoctor_id(rs.getString("doctor_id"));
				doctor.setDoctor_pw(rs.getString("doctor_pw"));
				doctor.setDoctor_name(rs.getString("doctor_name"));
				doctor.setHospital_id(rs.getString("hospital_id"));
				doctor.setOffice_number(rs.getString("office_number"));

				return doctor;
			}

		});

	}

	public boolean addDoctor(Doctor doctor) {

		String doctor_id = doctor.getDoctor_id();
		String doctor_pw = doctor.getDoctor_pw();
		doctor_pw = "{noop}" + doctor_pw;
		String doctor_name = doctor.getDoctor_name();
		String hospital_id = doctor.getHospital_id();
		String office_number = doctor.getOffice_number();

		String sqlStatement = "insert into doctor (doctor_id, doctor_pw, doctor_name, hospital_id, office_number, enabled, authority) values (?, ?, ?, ?, ?, ?, ?)";
		return (jdbcTemplate.update(sqlStatement,
				new Object[] { doctor_id, doctor_pw, doctor_name, hospital_id, office_number, 1, "ROLE_DR" }) == 1);
	}

	public boolean checkDoctorExistence(String doctor_id) {
		String sqlStatement = "select count(*) from doctor where doctor_id=?";

		if (jdbcTemplate.queryForObject(sqlStatement, new Object[] { doctor_id }, Integer.class) > 0)
			return true;
		else
			return false;
	}

	public boolean deleteDoctor(String doctor_id) {

		String sqlStatement = "delete from doctor where doctor_id=?";

		return (jdbcTemplate.update(sqlStatement, new Object[] { doctor_id }) == 1);
	}

	public boolean updateDoctor(@Valid Doctor doctor) {

		String doctor_id = doctor.getDoctor_id();
		String office_number = doctor.getOffice_number();

		String sqlStatement = "update doctor set office_number=? where doctor_id=? ";
		return (jdbcTemplate.update(sqlStatement,
				new Object[] { office_number, doctor_id }) == 1);
	}
}

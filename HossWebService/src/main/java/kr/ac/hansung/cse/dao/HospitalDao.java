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

import kr.ac.hansung.cse.model.Hospital;

@Repository
public class HospitalDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Hospital getHospitalByHospital_id(String hospital_id) {

		String sqlStatement = "select * from hospital where hospital_id=?";

		return jdbcTemplate.queryForObject(sqlStatement, new Object[] { hospital_id }, new RowMapper<Hospital>() {

			@Override
			public Hospital mapRow(ResultSet rs, int rowNum) throws SQLException {
				Hospital hospital = new Hospital();

				hospital.setHospital_id(rs.getString("hospital_id"));
				hospital.setHospital_pw(rs.getString("hospital_pw"));
				hospital.setHospital_name(rs.getString("hospital_name"));
				hospital.setAddress(rs.getString("address"));
				hospital.setDepartment(rs.getString("department"));
				hospital.setHours(rs.getString("hours"));
				hospital.setRejected_time(rs.getString("rejected_time"));

				return hospital;
			}

		});

	}

	public List<Hospital> getHospitals() {

		String sqlStatement = "select * from hospital";

		return jdbcTemplate.query(sqlStatement, new RowMapper<Hospital>() {

			@Override
			public Hospital mapRow(ResultSet rs, int rowNum) throws SQLException {
				Hospital hospital = new Hospital();

				hospital.setHospital_id(rs.getString("hospital_id"));
				hospital.setHospital_pw(rs.getString("hospital_pw"));
				hospital.setHospital_name(rs.getString("hospital_name"));
				hospital.setAddress(rs.getString("address"));
				hospital.setDepartment(rs.getString("department"));
				hospital.setHours(rs.getString("hours"));
				hospital.setRejected_time(rs.getString("rejected_time"));

				return hospital;
			}
		});

	}

	public boolean addHospital(Hospital hospital) {

		String hospital_id = hospital.getHospital_id();
		String hospital_pw = hospital.getHospital_pw();
		hospital_pw = "{noop}" + hospital_pw;
		String hospital_name = hospital.getHospital_name();
		String address = hospital.getAddress();
		String department = hospital.getDepartment();
		String hours = hospital.getHours();

		String sqlStatement = "insert into hospital (hospital_id, hospital_pw, hospital_name, address, department, hours, enabled, authority) values (?, ?, ?, ?, ?, ?, ?, ?)";
		return (jdbcTemplate.update(sqlStatement, new Object[] { hospital_id, hospital_pw, hospital_name, address,
				department, hours, 1, "ROLE_HOS" }) == 1);
	}

	public boolean checkHospitalExistence(String hospital_id) {
		String sqlStatement = "select count(*) from hospital where hospital_id=?";

		if (jdbcTemplate.queryForObject(sqlStatement, new Object[] { hospital_id }, Integer.class) > 0)
			return true;
		else
			return false;
	}

	public boolean updateHospital(@Valid Hospital hospital) {

		String hospital_id = hospital.getHospital_id();
		String hospital_pw = hospital.getHospital_pw();
		String hospital_name = hospital.getHospital_name();
		String address = hospital.getAddress();
		String department = hospital.getDepartment();
		String hours = hospital.getHours();
		String rejected_time = hospital.getRejected_time();

		String sqlStatement = "update hospital set hospital_pw=?, hospital_name=?, address=?, department=?, hours=?, rejected_time=?"
				+ " where hospital_id=?";
		return (jdbcTemplate.update(sqlStatement,
				new Object[] { hospital_pw, hospital_name, address, department, hours, rejected_time, hospital_id }) == 1);

	}

}

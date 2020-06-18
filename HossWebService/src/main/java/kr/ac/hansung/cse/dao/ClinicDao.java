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

import kr.ac.hansung.cse.model.Clinic;

@Repository
public class ClinicDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Clinic getClinicByClinic_number(String clinic_number) {

		String sqlStatement = "select * from clinic where clinic_number=?";

		return jdbcTemplate.queryForObject(sqlStatement, new Object[] { clinic_number },
				new RowMapper<Clinic>() {

					@Override
					public Clinic mapRow(ResultSet rs, int rowNum) throws SQLException {
						Clinic clinic = new Clinic();

						clinic.setClinic_number(rs.getInt("clinic_number"));
						clinic.setUser_id(rs.getString("user_id"));
						clinic.setDoctor_id(rs.getString("doctor_id"));
						clinic.setClinic_date(rs.getString("clinic_date"));
						clinic.setClinic_treatment(rs.getString("clinic_treatment"));
						clinic.setPrescription_drugname(rs.getString("prescription_drugname"));
						clinic.setPrescription_dosage(rs.getString("prescription_dosage"));
						clinic.setPrescription_caution(rs.getString("prescription_caution"));

						return clinic;
					}

				});

	}

	public List<Clinic> getClinicsByUser_id(String user_id) {

		String sqlStatement = "select * from clinic where user_id=?";

		return jdbcTemplate.query(sqlStatement, new Object[] { user_id }, new RowMapper<Clinic>() {

			@Override
			public Clinic mapRow(ResultSet rs, int rowNum) throws SQLException {
				Clinic clinic = new Clinic();

				clinic.setClinic_number(rs.getInt("clinic_number"));
				clinic.setUser_id(rs.getString("user_id"));
				clinic.setDoctor_id(rs.getString("doctor_id"));
				clinic.setClinic_date(rs.getString("clinic_date"));
				clinic.setClinic_treatment(rs.getString("clinic_treatment"));
				clinic.setPrescription_drugname(rs.getString("prescription_drugname"));
				clinic.setPrescription_dosage(rs.getString("prescription_dosage"));
				clinic.setPrescription_caution(rs.getString("prescription_caution"));

				return clinic;
			}

		});

	}

	public List<Clinic> getClinicsByDoctor_id(String doctor_id) {

		String sqlStatement = "select * from clinic where doctor_id=?";

		return jdbcTemplate.query(sqlStatement, new Object[] { doctor_id }, new RowMapper<Clinic>() {

			@Override
			public Clinic mapRow(ResultSet rs, int rowNum) throws SQLException {
				Clinic clinic = new Clinic();

				clinic.setClinic_number(rs.getInt("clinic_number"));
				clinic.setUser_id(rs.getString("user_id"));
				clinic.setDoctor_id(rs.getString("doctor_id"));
				clinic.setClinic_date(rs.getString("clinic_date"));
				clinic.setClinic_treatment(rs.getString("clinic_treatment"));
				clinic.setPrescription_drugname(rs.getString("prescription_drugname"));
				clinic.setPrescription_dosage(rs.getString("prescription_dosage"));
				clinic.setPrescription_caution(rs.getString("prescription_caution"));

				return clinic;
			}

		});

	}

	public boolean addClinic(Clinic clinic) {

		int clinic_number = clinic.getClinic_number();
		String user_id = clinic.getUser_id();
		String doctor_id = clinic.getDoctor_id();
		String clinic_date = clinic.getClinic_date();
		String clinic_treatment = clinic.getClinic_treatment();
		String prescription_drugname = clinic.getPrescription_drugname();
		String prescription_dosage = clinic.getPrescription_dosage();
		String prescription_caution = clinic.getPrescription_caution();

		String sqlStatement = "insert into clinic ( clinic_number, user_id, doctor_id, clinic_date, clinic_treatment, prescription_drugname, prescription_dosage, prescription_caution) values (?, ?, ?, ?, ?, ?, ?, ?)";
		return (jdbcTemplate.update(sqlStatement,
				new Object[] { clinic_number, user_id, doctor_id, clinic_date, clinic_treatment, prescription_drugname, prescription_dosage, prescription_caution }) == 1);
	}

	public boolean updateClinic(@Valid Clinic clinic) {

		int clinic_number = clinic.getClinic_number();
		String user_id = clinic.getUser_id();
		String doctor_id = clinic.getDoctor_id();
		String clinic_date = clinic.getClinic_date();
		String clinic_treatment = clinic.getClinic_treatment();
		String prescription_drugname = clinic.getPrescription_drugname();
		String prescription_dosage = clinic.getPrescription_dosage();
		String prescription_caution = clinic.getPrescription_caution();

		String sqlStatement = "update clinic set clinic_date=?, clinic_treatment=?, prescription_drugname=?, prescription_dosage=?, prescription_caution=?"
				+ " where clinic_number=? and user_id=? and doctor_id=? ";
		return (jdbcTemplate.update(sqlStatement, new Object[] { clinic_date, clinic_treatment,
				prescription_drugname, prescription_dosage, prescription_caution, clinic_number, user_id, doctor_id }) == 1);

	}
	

}

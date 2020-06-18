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

import kr.ac.hansung.cse.model.Office;

@Repository
public class OfficeDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Office> getOfficesByHospital_id(String hospital_id) {

		String sqlStatement = "select * from office where hospital_id=?";

		return jdbcTemplate.query(sqlStatement, new Object[] { hospital_id }, new RowMapper<Office>() {

			@Override
			public Office mapRow(ResultSet rs, int rowNum) throws SQLException {
				Office office = new Office();

				office.setHospital_id(rs.getString("hospital_id"));
				office.setOffice_number(rs.getString("office_number"));
				office.setOffice_department(rs.getString("office_department"));
				office.setWaiting_number(rs.getString("waiting_number"));

				return office;
			}

		});

	}
	
	public Office getOfficeByHospital_idOffice_number(String hospital_id, String office_number) {

		String sqlStatement = "select * from office where hospital_id=? and office_number=?";

		return jdbcTemplate.queryForObject(sqlStatement, new Object[] { hospital_id, office_number }, new RowMapper<Office>() {

			@Override
			public Office mapRow(ResultSet rs, int rowNum) throws SQLException {
				Office office = new Office();

				office.setHospital_id(rs.getString("hospital_id"));
				office.setOffice_number(rs.getString("office_number"));
				office.setOffice_department(rs.getString("office_department"));
				office.setWaiting_number(rs.getString("waiting_number"));

				return office;
			}

		});

	}

	public boolean addOffice(Office office) {

		String hospital_id = office.getHospital_id();
		String office_number = office.getOffice_number();
		String office_department = office.getOffice_department();
		String waiting_number = office.getWaiting_number();

		String sqlStatement = "insert into office (hospital_id, office_number, office_department, waiting_number) values (?, ?, ?, ?)";
		return (jdbcTemplate.update(sqlStatement,
				new Object[] { hospital_id, office_number, office_department, waiting_number }) == 1);
	}

	public boolean deleteOffice(String hospital_id, String office_number) {

		String sqlStatement = "delete from office where hospital_id=? and office_number=?";

		return (jdbcTemplate.update(sqlStatement, new Object[] { hospital_id, office_number }) == 1);
	}

	public boolean updateOffice(@Valid Office office) {

		String hospital_id = office.getHospital_id();
		String office_number = office.getOffice_number();
		String office_department = office.getOffice_department();
		String waiting_number = office.getWaiting_number();

		String sqlStatement = "update office set office_department=?, waiting_number=?"
				+ " where hospital_id=? and office_number=? ";
		return (jdbcTemplate.update(sqlStatement,
				new Object[] { office_department, waiting_number, hospital_id, office_number }) == 1);
	}
	
}

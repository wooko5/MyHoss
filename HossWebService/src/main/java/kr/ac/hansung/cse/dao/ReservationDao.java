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

import kr.ac.hansung.cse.model.Reservation;

@Repository
public class ReservationDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Reservation> getWaitingReservationsByUser_id(String user_id) {

		String sqlStatement = "select * from reservation where user_id=? and (reservation_status!=? or reservation_status is null)";

		return jdbcTemplate.query(sqlStatement, new Object[] { user_id, "완료" }, new RowMapper<Reservation>() {

			@Override
			public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
				Reservation reservation = new Reservation();

				reservation.setUser_id(rs.getString("user_id"));
				reservation.setHospital_id(rs.getString("hospital_id"));
				reservation.setReservation_number(rs.getInt("reservation_number"));
				reservation.setReservation_time(rs.getString("reservation_time"));
				reservation.setReservation_status(rs.getString("reservation_status"));
				reservation.setReservation_estimated_time(rs.getString("reservation_estimated_time"));
				reservation.setOffice_number(rs.getString("office_number"));

				return reservation;
			}

		});

	}

	public List<Reservation> getWaitingReservationsByHospital_id(String hospital_id) {

		String sqlStatement = "select * from reservation where hospital_id=? and (reservation_status!=? or reservation_status is null)";

		return jdbcTemplate.query(sqlStatement, new Object[] { hospital_id, "완료" },
				new RowMapper<Reservation>() {

					@Override
					public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
						Reservation reservation = new Reservation();

						reservation.setUser_id(rs.getString("user_id"));
						reservation.setHospital_id(rs.getString("hospital_id"));
						reservation.setReservation_number(rs.getInt("reservation_number"));
						reservation.setReservation_time(rs.getString("reservation_time"));
						reservation.setReservation_status(rs.getString("reservation_status"));
						reservation.setReservation_estimated_time(rs.getString("reservation_estimated_time"));
						reservation.setOffice_number(rs.getString("office_number"));

						return reservation;
					}

				});

	}

	public List<Reservation> getAcceptedReservationsByHospital_idOffice_number(String hospital_id,
			String office_number) {

		String sqlStatement = "select * from reservation where hospital_id=? and office_number=? and reservation_status=?";

		return jdbcTemplate.query(sqlStatement, new Object[] { hospital_id, office_number, "승인" },
				new RowMapper<Reservation>() {

					@Override
					public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
						Reservation reservation = new Reservation();

						reservation.setUser_id(rs.getString("user_id"));
						reservation.setHospital_id(rs.getString("hospital_id"));
						reservation.setReservation_number(rs.getInt("reservation_number"));
						reservation.setReservation_time(rs.getString("reservation_time"));
						reservation.setReservation_status(rs.getString("reservation_status"));
						reservation.setReservation_estimated_time(rs.getString("reservation_estimated_time"));
						reservation.setOffice_number(rs.getString("office_number"));

						return reservation;
					}

				});

	}

	public Reservation getReservationByReservation_number(String reservation_number) {

		String sqlStatement = "select * from reservation where reservation_number=?";

		return jdbcTemplate.queryForObject(sqlStatement, new Object[] { reservation_number },
				new RowMapper<Reservation>() {

					@Override
					public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
						Reservation reservation = new Reservation();

						reservation.setUser_id(rs.getString("user_id"));
						reservation.setHospital_id(rs.getString("hospital_id"));
						reservation.setReservation_number(rs.getInt("reservation_number"));
						reservation.setReservation_time(rs.getString("reservation_time"));
						reservation.setReservation_status(rs.getString("reservation_status"));
						reservation.setReservation_estimated_time(rs.getString("reservation_estimated_time"));
						reservation.setOffice_number(rs.getString("office_number"));

						return reservation;
					}

				});

	}

	public boolean addReservation(Reservation reservation) {

		String user_id = reservation.getUser_id();
		String hospital_id = reservation.getHospital_id();
		int reservation_number = reservation.getReservation_number();
		String reservation_time = reservation.getReservation_time();
		String reservation_status = reservation.getReservation_status();
		String reservation_estimated_time = reservation.getReservation_estimated_time();
		String office_number = reservation.getOffice_number();

		String sqlStatement = "insert into reservation (user_id, hospital_id, reservation_number, reservation_time, reservation_status, reservation_estimated_time, office_number) values (?, ?, ?, ?, ?, ?, ?)";
		return (jdbcTemplate.update(sqlStatement, new Object[] { user_id, hospital_id, reservation_number,
				reservation_time, reservation_status, reservation_estimated_time, office_number }) == 1);
	}

	public boolean updateReservation(@Valid Reservation reservation) {

		String user_id = reservation.getUser_id();
		String hospital_id = reservation.getHospital_id();
		int reservation_number = reservation.getReservation_number();
		String reservation_time = reservation.getReservation_time();
		String reservation_status = reservation.getReservation_status();
		String reservation_estimated_time = reservation.getReservation_estimated_time();
		String office_number = reservation.getOffice_number();

		String sqlStatement = "update reservation set reservation_time=?, reservation_status=?, reservation_estimated_time=?, office_number=?"
				+ " where user_id=? and hospital_id=? and reservation_number=? ";
		return (jdbcTemplate.update(sqlStatement, new Object[] { reservation_time, reservation_status,
				reservation_estimated_time, office_number, user_id, hospital_id, reservation_number }) == 1);

	}

	public boolean deleteReservation(String reservation_number) {

		String sqlStatement = "delete from reservation where reservation_number=?";

		return (jdbcTemplate.update(sqlStatement, new Object[] { reservation_number }) == 1);
	}

}

package kr.ac.hansung.cse.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.ac.hansung.cse.model.User;

@Repository
public class UserDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public boolean addUser(User user) {

		String user_id = user.getUser_id();
		String user_pw = user.getUser_pw();
		String user_name = user.getUser_name();
		String user_birth = user.getUser_birth();
		String user_Phone = user.getUser_Phone();
		String user_address = user.getUser_address();
		String user_email = user.getUser_email();

		String sqlStatement = "insert into user ( user_id, user_pw, user_name, user_birth, user_Phone, user_address, user_email) values (?, ?, ?, ?, ?, ?, ?)";
		return (jdbcTemplate.update(sqlStatement,
				new Object[] { user_id, user_pw, user_name, user_birth, user_Phone, user_address, user_email }) == 1);
	}

	public boolean checkUserExistence(String user_id) {
		String sqlStatement = "select count(*) from user where user_id=?";

		if (jdbcTemplate.queryForObject(sqlStatement, new Object[] { user_id }, Integer.class) > 0)
			return true;
		else
			return false;
	}

	public boolean compareUserIdPw(String user_id, String user_pw) {
		String sqlStatement = "select count(*) from user where user_id=? and user_pw=?";

		if (jdbcTemplate.queryForObject(sqlStatement, new Object[] { user_id, user_pw }, Integer.class) > 0)
			return true;
		else
			return false;
	}

	public User getUserByUser_id(String user_id) {

		String sqlStatement = "select * from user where user_id=?";

		return jdbcTemplate.queryForObject(sqlStatement, new Object[] { user_id }, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();

				user.setUser_id(rs.getString("user_id"));
				user.setUser_pw(rs.getString("user_pw"));
				user.setUser_name(rs.getString("user_name"));
				user.setUser_birth(rs.getString("user_birth"));
				user.setUser_Phone(rs.getString("user_Phone"));
				user.setUser_address(rs.getString("user_address"));
				user.setUser_email(rs.getString("user_email"));

				return user;
			}

		});

	}

}

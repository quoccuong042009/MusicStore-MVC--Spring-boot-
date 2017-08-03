package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.User;

@Repository
public class UserRepository {
	@Autowired
	private JdbcTemplate jdbc;
	
	public void insertUser(User user){
		String sql = "insert into user (username, password, email,role) values (?,?,?,?)";
		jdbc.update(sql,new Object[]{user.getUsername(),user.getPassword(),user.getEmailAddress(),user.getRole()});
	}

	public int takeNewestUser() {
		String sql = "select max(user_id) from user";
		return jdbc.queryForObject(sql, Integer.class);
	}

	public String getNameByUserId(String userId) {
		String sql = "select username from user where user_id = ?";
		return jdbc.queryForObject(sql, new Object[]{userId}, String.class);
	}

	public List<User> getAll() {
		String sql = "select * from user";
		return jdbc.query(sql, (rs, i) -> new User(rs.getInt("user_id")
				,rs.getString("username")
				,rs.getString("password")
				,""
				,rs.getString("email")
				,rs.getInt("role")));
	}
}

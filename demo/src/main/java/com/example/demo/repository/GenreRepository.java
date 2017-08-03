package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Genre;



@Repository
public class GenreRepository {
	@Autowired
	private JdbcTemplate jdbc;


	public List<Genre> getAllGenreOrderById() {
		String sql = "select * from genre order by id";
		return jdbc.query(sql, (rs, i) -> new Genre(rs.getInt("id"), rs.getString("name"), "",new ArrayList<>()));
	}
	
	public int getGenreIDByName(String name){
		String sql = "select id from genre where name = ?";
		return jdbc.queryForObject(sql, new Object[]{name}, Integer.class);
	}
	
	public String getGerneNameById(int id){
		String sql = "select name from genre where id = ?";
		return jdbc.queryForObject(sql, new Object[]{id}, String.class);
	}
}

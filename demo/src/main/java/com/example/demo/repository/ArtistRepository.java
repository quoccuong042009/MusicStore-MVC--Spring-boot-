package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Artist;

@Repository
public class ArtistRepository {
	@Autowired
	private JdbcTemplate jdbc;

	
	public String getArtistNameById(int artistIdSelected){
		String sql = "select name from artists where id = ?";
		return jdbc.queryForObject(sql, new Object[]{artistIdSelected}, String.class);
	}


	public List<Artist> getAll() {
		String sql = "select * from artists";
		return jdbc.query(sql, (rs, i) -> new Artist(
				rs.getInt("id"),
				rs.getString("name")));
	}
}

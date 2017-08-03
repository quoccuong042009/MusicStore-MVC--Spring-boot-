package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Genre;
import com.example.demo.domain.Album;
import com.example.demo.domain.Artist;

@Repository
public class AlbumRepository {
	@Autowired
	private JdbcTemplate jdbc;
	
	public List<Album> getAllAlbumByGenreID(String genreId){
		String sql = "select * from album where genre_id = ?";
		return jdbc.query(sql, new Object[]{genreId}, (rs, i) -> new Album(
				rs.getInt("id")
				, rs.getString("title")
				, rs.getDouble("price")
				, ""
				,new Genre()
				,new Artist()));
	}

	public String getTitleAlbumByAlbumId(String albumId) {
		return jdbc.queryForObject("select title from album where id = ?", new Object[]{albumId}, String.class);
	}
	
	public List<Album> getAllAlbum(){
		String sql = "SELECT album.id as al_id,album.title,album.price,"
					+ "artists.id as art_id, artists.name as art_name,"
					+ "genre.id as gen_id,genre.name as gen_name "
					+ "FROM album , artists, genre "
					+ "WHERE  album.artist_id = artists.id "
					+ "AND album.genre_id=genre.id "
					+ "ORDER BY al_id";
		List<Album> res = jdbc.query(sql, (rs, i) -> new Album(
				rs.getInt("al_id"),
				rs.getString("title"),
				rs.getDouble("price"),
				"",
				new Genre(rs.getInt("gen_id"), rs.getString("gen_name")),
				new Artist(rs.getInt("art_id"), rs.getString("art_name"))
				));
		return res;
	}
	

	public int getGenreIdByAlbumId(String albumId) {
		// TODO Auto-generated method stub
		return jdbc.queryForObject("select genre_id from album where id = ?", new Object[]{albumId}, Integer.class);
	}
	
	public int getArtistIdByAlbumId(String albumId) {
		// TODO Auto-generated method stub
		return jdbc.queryForObject("select artist_id from album where id = ?", new Object[]{albumId}, Integer.class);
	}

	public double getPriceAlbumByAlbumId(String albumId) {
		// TODO Auto-generated method stub
		return jdbc.queryForObject("select price from album where id = ?", new Object[]{albumId}, Double.class);
	}

	public void deleteAlbumByAlbumId(String albumId) {
		// TODO Auto-generated method stub
		jdbc.update("delete from album where id = ?" , albumId);
	}

	public void updateAlbumbyAlbumId(String albumId, String title, double price, int artist_id, int genre_id) {
		String sql = "update album set title = ?, price = ?, artist_id = ?, genre_id = ? where id = ?";
		jdbc.update(sql, new Object[]{title,price,artist_id,genre_id,albumId});
		
	}

	public void insertAlbum(String title, double price, int aritst_id, int genre_id) {
		String sql = "insert into album (title, price, artist_id, genre_id) values (?,?,?,?)";
		jdbc.update(sql,new Object[]{title,price,aritst_id,genre_id});
	}
	
}

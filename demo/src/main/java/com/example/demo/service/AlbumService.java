package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Album;
import com.example.demo.repository.AlbumRepository;

@Service
public class AlbumService {
	@Autowired
	AlbumRepository albumService;
	
	public List<Album> getAllAlbumByGenreID(String genreId){
		return albumService.getAllAlbumByGenreID(genreId);
	}

	public String getTitleAlbumByAlbumId(String albumId) {
		return albumService.getTitleAlbumByAlbumId(albumId);
	}

	public Object getAllAlbum() {
		// TODO Auto-generated method stub
		return albumService.getAllAlbum();
	}

	public int getGenreIdByAlbumId(String albumId) {
		// TODO Auto-generated method stub
		return albumService.getGenreIdByAlbumId(albumId);
	}

	public int getArtistIdByAlbumId(String albumId) {
		return albumService.getArtistIdByAlbumId(albumId);
		
	}

	public double getPriceAlbumByAlbumId(String albumId) {
		return albumService.getPriceAlbumByAlbumId(albumId);
	}
	
	public void deleteAlbumByAlbumId(String albumId){
		albumService.deleteAlbumByAlbumId(albumId);
	}
	
	public void updateAlbumbyAlbumId(String albumId, String title, double price, int artist_id, int genre_id){
		albumService.updateAlbumbyAlbumId(albumId,title,price,artist_id,genre_id);
	}
	
	public void insertAlbum(String title, double price, int aritst_id, int genre_id){
		albumService.insertAlbum(title,price,aritst_id,genre_id);
	}
}

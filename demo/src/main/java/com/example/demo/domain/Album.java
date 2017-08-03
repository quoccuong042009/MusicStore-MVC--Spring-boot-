package com.example.demo.domain;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class Album {
	private int AlbumId ;
	
    @Size(min=3, max=30) 
	private String Title ;
	
    @NotNull
    @DecimalMax("200.00") @DecimalMin("0.01") 
	private double Price ;
    
	private String AlbumArtUrl;
	private Genre Genre ; 
	private Artist Artist;
	
	public Album(){}

	public Album(int albumId, String title, double price, String albumArtUrl,
			com.example.demo.domain.Genre genre, com.example.demo.domain.Artist artist) {
		super();
		AlbumId = albumId;
		Title = title;
		Price = price;
		AlbumArtUrl = albumArtUrl;
		Genre = genre;
		Artist = artist;
	}

	public int getAlbumId() {
		return AlbumId;
	}

	public void setAlbumId(int albumId) {
		AlbumId = albumId;
	}
	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
	}

	public String getAlbumArtUrl() {
		return AlbumArtUrl;
	}

	public void setAlbumArtUrl(String albumArtUrl) {
		AlbumArtUrl = albumArtUrl;
	}

	public Genre getGenre() {
		return Genre;
	}

	public void setGenre(Genre genre) {
		Genre = genre;
	}

	public Artist getArtist() {
		return Artist;
	}

	public void setArtist(Artist artist) {
		Artist = artist;
	}
	
	
	
	
}

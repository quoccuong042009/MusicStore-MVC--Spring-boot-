package com.example.demo.domain;

import java.util.ArrayList;
import java.util.List;

public class Genre {
	private int GenreID;
	private String Name;
	private String Description;
	private List<Album> Albums;
	
	public Genre(){}
	
	public Genre(int genreID, String name){
		GenreID = genreID;
		Name = name;
		Description ="";
		Albums = new ArrayList<Album>();
	}
	
	public Genre(int genreID, String name, String description, List<Album> albums) {
		super();
		GenreID = genreID;
		Name = name;
		Description = description;
		Albums = albums;
	}

	public int getGenreID() {
		return GenreID;
	}

	public void setGenreID(int genreID) {
		GenreID = genreID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public List<Album> getAlbums() {
		return Albums;
	}

	public void setAlbums(List<Album> albums) {
		Albums = albums;
	}
	
	
}

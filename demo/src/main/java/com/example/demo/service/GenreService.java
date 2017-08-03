package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Genre;
import com.example.demo.repository.GenreRepository;

@Service
public class GenreService {
	@Autowired
	GenreRepository service;
	
	public List<Genre> getAll(){
		return service.getAllGenreOrderById();
	}
	
	public int getGenreIDbyGenreName(String name){
		return service.getGenreIDByName(name);
	}
	
	public String getGenreNameByid(int id){
		return service.getGerneNameById(id);
	}

}

package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Artist;
import com.example.demo.repository.ArtistRepository;

@Service
public class ArtistService {
	@Autowired
	ArtistRepository service;
	
	public String getArtistNameById(int id){
		return service.getArtistNameById(id);
	}

	public  List<Artist> getAll() {
		// TODO Auto-generated method stub
		return service.getAll();
	}

}

package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;



@Service
public class UserService {

	@Autowired
	UserRepository service;
	
	public void insertUser(User user){
		service.insertUser(user);
	}

	public int takeNewestUser() {
		// TODO Auto-generated method stub
		return service.takeNewestUser();
	}

	public String getNameByUserId(String userId) {
		// TODO Auto-generated method stub
		return service.getNameByUserId(userId);
	}

	public List<User> getAll() {
		// TODO Auto-generated method stub
		return service.getAll();
	}
}

package com.example.demo.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;



public class User{
	private int UserId ;
	
    @NotNull
    @Size(min=5, max=30) 
	private String Username ;
    
    @Size(min=5, max=30)
	private String Password;
    
    @Size(min=5, max=30)
	private String PasswordConfirmation;
	
    @NotNull
    @Email
	private String emailAddress;
	
    private int role;
    
    public User(){}

	public User(int userId, String username, String password, String passwordConfirmation, String emailAddress,
			int role) {
		super();
		UserId = userId;
		Username = username;
		Password = password;
		PasswordConfirmation = passwordConfirmation;
		this.emailAddress = emailAddress;
		this.role = role;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getPasswordConfirmation() {
		return PasswordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		PasswordConfirmation = passwordConfirmation;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	};
    
	
}

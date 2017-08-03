package com.example.demo.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class OrderDetail {
	private int OrderId;
	private int UserId;
	private String Username;
    @NotNull
    @Size(min=2, max=30) 
	private String FirstName ;
    @NotNull
    @Size(min=2, max=30) 
	private String LastName ;
    @NotNull
    @Size(min=5, max=30) 
	private String Address ;
    @NotNull
    @Size(min=5, max=30) 
	private String City ;
    @NotNull
    @Size(min=2, max=30) 
	private String State ;
    @NotNull
    @Size(min=5, max=30) 
	private String PostalCode;
    @NotNull
    @Size(min=5, max=30) 
	private String Country ;
    @NotNull
    @Size(min=5, max=30) 
	private String Phone ;
    @NotNull
    @Email
	private String Email ;
	private double Total ;
	
    public OrderDetail(){};
    
	public OrderDetail(int orderId, int userId, String username, String firstName, String lastName, String address,
			String city, String state, String postalCode, String country, String phone, String email, double total) {
		super();
		OrderId = orderId;
		UserId = userId;
		Username = username;
		FirstName = firstName;
		LastName = lastName;
		Address = address;
		City = city;
		State = state;
		PostalCode = postalCode;
		Country = country;
		Phone = phone;
		Email = email;
		Total = total;
	}
	public int getOrderId() {
		return OrderId;
	}
	public void setOrderId(int orderId) {
		OrderId = orderId;
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
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getPostalCode() {
		return PostalCode;
	}
	public void setPostalCode(String postalCode) {
		PostalCode = postalCode;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public double getTotal() {
		return Total;
	}
	public void setTotal(double total) {
		Total = total;
	}
	
	
}

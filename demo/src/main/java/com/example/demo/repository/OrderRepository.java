package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.OrderDetail;

@Repository
public class OrderRepository {
	@Autowired
	private JdbcTemplate jdbc;

	public void insertOrder(OrderDetail order) {
		String sql = "insert into order_details (user_id"
				+ ", username"
				+ ", firstname"
				+ ", lastname"
				+ ", address"
				+ ", city"
				+ ", state"
				+ ", postalcode"
				+ ", country"
				+ ", phone"
				+ ", email"
				+ ", total) values (?,?,?,?,?,?,?,?,?,?,?,?)";
		
		jdbc.update(sql,new Object[]{order.getUserId(),order.getUsername(),order.getFirstName(),order.getLastName()
				,order.getAddress(),order.getCity(),order.getState(),order.getPostalCode(),order.getCountry(),order.getPhone()
				,order.getEmail(),order.getTotal()});
		
	}

	public int getLastedOrderId() {
		String sql = "select max(order_id) from order_details";
		return jdbc.queryForObject(sql, Integer.class);
	}
	
	
}

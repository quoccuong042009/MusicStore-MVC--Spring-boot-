package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.OrderDetail;
import com.example.demo.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	OrderRepository orderService;
	
	public void insertOrder(OrderDetail order){
		orderService.insertOrder(order);
	}

	public int getLastedOrderId() {
		// TODO Auto-generated method stub
		return orderService.getLastedOrderId();
	}
}

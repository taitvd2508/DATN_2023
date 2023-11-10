package com.datn.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import com.datn.model.Order;

public interface OrderService {
	public Order create(JsonNode orderData) ;
	
	public Order findById(Long id) ;
	
	public List<Order> findByUsername(String username) ;
	
	public Order updateSTT(Order order);
}

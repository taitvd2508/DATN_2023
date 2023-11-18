package com.datn.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import com.datn.model.Order;

public interface OrderService {
	public Order create(JsonNode orderData) ;
	
	public Order findById(Long id) ;
	
	public List<Order> findByUsername(String username) ;
	
	public List<Order> findAll() ;
	
	public Order updateSTT(Order order);

	public void create(Order order);

	public Order update(Order order);

	public void delete(Long id);
}

package com.datn.service;

import java.util.List;

import com.datn.model.OrderGuest;
import com.fasterxml.jackson.databind.JsonNode;

public interface OrderGuestService {
	public OrderGuest createOrderGuest(JsonNode orderData) ;
	
	public OrderGuest findById(Long id) ;
	
	public OrderGuest updateSTT(OrderGuest orderGuest);
	
	public List<OrderGuest> findByPhonenumberAndAddress(String phonenumber, String address);
}

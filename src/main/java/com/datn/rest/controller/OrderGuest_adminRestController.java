package com.datn.rest.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datn.model.OrderGuest;
import com.datn.model.OrderStatus;
import com.datn.service.OrderGuestService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/ordersGuestAdmin")
public class OrderGuest_adminRestController {
	@Autowired
	OrderGuestService orderGuestService;
	
	// ## ADMIN ## //
	@GetMapping
	public List<OrderGuest> getAll() {
		return orderGuestService.findAll();
	}
	
	@GetMapping("{id}")
	public OrderGuest getOne(@PathVariable("id") Long id) {
		return orderGuestService.findById(id);
	}
	
	@PostMapping
	public OrderGuest post(@RequestBody OrderGuest orderGuest) {
		orderGuestService.create(orderGuest);
		return orderGuest;
	}
	
	@PutMapping("{id}")
	public OrderGuest put(@PathVariable("id") Long id, @RequestBody OrderGuest orderGuest) {
		return orderGuestService.update(orderGuest);
	}
	
	@PutMapping("status/{id}")
	public OrderGuest putstatus(@PathVariable("id") Long id) {
		OrderGuest orderGuest = orderGuestService.findById(id);
		OrderStatus orderStatus = new OrderStatus();
		orderStatus.setId("DXN");
		OrderGuest orderGuestNew = new OrderGuest();
		orderGuestNew.setId(orderGuest.getId());
		orderGuestNew.setCreateDate(orderGuest.getCreateDate());
		orderGuestNew.setPhonenumber(orderGuest.getPhonenumber());
		orderGuestNew.setAddress(orderGuest.getAddress());
		orderGuestNew.setOrderStatus(orderStatus);
		return orderGuestService.updateSTT(orderGuestNew);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Long id) {
		orderGuestService.delete(id);
	}
}

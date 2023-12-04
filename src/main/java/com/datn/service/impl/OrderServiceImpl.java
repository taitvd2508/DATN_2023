package com.datn.service.impl;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.datn.dao.AccountDAO;
import com.datn.dao.AuthorityDAO;
import com.datn.dao.OrderDAO;
import com.datn.dao.OrderDetailDAO;
import com.datn.dao.RoleDAO;
import com.datn.model.Account;
import com.datn.model.Authority;
import com.datn.model.Order;
import com.datn.model.OrderDetail;
import com.datn.model.Role;
import com.datn.service.AccountService;
import com.datn.service.MailerService;
import com.datn.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	OrderDAO orderDAO;
	
	@Autowired
	OrderDetailDAO orderDetailDAO;
	
	@Autowired
	MailerService mailerService;
	
	@Autowired
	AccountService accountService;
	
	public Order create(JsonNode orderData) {
		ObjectMapper mapper = new ObjectMapper(); // sử dụng objectmapper để chuyển json thành các đối tượng cần thiết

		Order order = mapper.convertValue(orderData, Order.class);  // chuyển orderData(json) thành order
		TypeReference<Account> typeReference = new TypeReference<Account>() {};
		Account account = mapper.convertValue(orderData.get("account"), typeReference);  // dùng convertValue chuyển json thành account
		try {
		if(accountService.findByUsername(account.getUsername()) == null) { // ch tồn tại
			accountService.autoCreate(account); // tự tạo account cho ng mua lần đầu
			mailerService.sendEmail(account, "first_Purchase"); // gửi email
			orderDAO.save(order); // tạo đơn hàng
		}else {
			orderDAO.save(order);
		}
		TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {};
		List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type) // lấy orderdetails // dùng convertValue chuyển json thành list orderdetails
				.stream().peek(d -> d.setOrder(order)).collect(Collectors.toList());
		orderDetailDAO.saveAll(details); // dùng saveAll() để lưu nhiều orderDetails 1 lúc
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return order; // trả về order vừa tạo ra trong CSDL
	}
	
	public Order findById(Long id) {
		return orderDAO.findById(id).get();
	}
	
	// find đơn hàng theo tên người dùng khi đã đăng nhập
	public List<Order> findByUsername(String username) {
		return orderDAO.findByUsername(username);
	}

	@Override
	public Order updateSTT(Order order) {
		return orderDAO.save(order);
	}

	@Override
	public List<Order> findAll() {
		return orderDAO.findAll();
	}

	@Override
	public void create(Order order) {
		orderDAO.save(order);
	}

	@Override
	public Order update(Order order) {
		return orderDAO.save(order);
	}

	@Override
	public void delete(Long id) {
		orderDAO.deleteById(id);
	}

	@Override
	public Order findFirstByOrderByIdDesc() {
		return orderDAO.findFirstByOrderByIdDesc();
	}

	@Override
	public List<Order> findAllIdDESC() {
		return orderDAO.findAllIdDESC();
	}
}

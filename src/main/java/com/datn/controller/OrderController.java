package com.datn.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.datn.model.Account;
import com.datn.model.Order;
import com.datn.model.OrderStatus;
import com.datn.service.AccountService;
import com.datn.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	OrderService orderService;
	@Autowired
	AccountService accountService;
	@Autowired
	HttpServletRequest request;
	
	@RequestMapping("/order/checkout")
	public String checkout() {
		return "order/checkout";
	}

	@RequestMapping("/order/detail/{id}")
	public String detail(Model model, @PathVariable("id") Long id) {
		model.addAttribute("order", orderService.findById(id));
		return "order/detail";
	}
	
	@RequestMapping("/order/list")
	public String list(Model model) {
		String username = request.getRemoteUser();
		model.addAttribute("orders", orderService.findByUsername(username));
		return "order/list";
	}
	
	@RequestMapping("/order/list_check")
	public String listcheck(Model model) {
		return "order/list_nologin";
	}
	
	@RequestMapping("/order/listCheck")
	public String list(Model model, @RequestParam("username") String username) {
		model.addAttribute("username", username);
		List<Order> orders = orderService.findByUsername(username);
		model.addAttribute("orders", orders);
		if(accountService.findByUsername(username) == null) { // KO CÓ USERNAME NÀY
			model.addAttribute("mess", "Username không tồn tai !");
		}else if(orders.size() == 0) {
			model.addAttribute("mess", "Chưa có đơn hàng nào !");
		}
		return "order/list_nologin";
	}
	
	@RequestMapping("/order/cancel/{id}")
	public String cancel(@PathVariable("id") Long id) {
		Order order = orderService.findById(id);
		//
		OrderStatus orderStatus = new OrderStatus();
		orderStatus.setId("DH");
		//
		Order orderCancel = new Order();
		orderCancel.setId(order.getId());
		orderCancel.setCreateDate(order.getCreateDate());
		orderCancel.setPhonenumber(order.getPhonenumber());
		orderCancel.setAddress(order.getAddress());
		orderCancel.setOrderStatus(orderStatus);
		orderCancel.setOrderMethod(order.getOrderMethod());
		orderCancel.setAccount(accountService.findById(request.getRemoteUser()));
		orderService.updateSTT(orderCancel);
		return "redirect:/order/list";
	}
}

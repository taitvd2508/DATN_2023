package com.datn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.datn.service.VoucherService;

@Controller
public class VoucherController {
	@Autowired
	VoucherService voucherService;
	
	@RequestMapping("/voucher")
	public String allVoucher(Model model) {
		model.addAttribute("vouchers", voucherService.findAll());
		return "voucher/list";
	}
}

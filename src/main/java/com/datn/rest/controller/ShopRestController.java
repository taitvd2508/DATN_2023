package com.datn.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datn.model.Product;
import com.datn.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/shop")
public class ShopRestController {
	@Autowired
	ProductService productService;
	
	@GetMapping("{id}") // get theo id để bỏ sp vào giỏ hàng
	public Product getOne(@PathVariable("id") Integer id) {
		return productService.findById(id); // lây sp theo id
	}
}

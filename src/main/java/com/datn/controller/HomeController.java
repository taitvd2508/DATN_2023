package com.datn.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.datn.model.Product;
import com.datn.service.ProductService;

@Controller
public class HomeController {
	@Autowired
	ProductService productService;
	
	@RequestMapping("/home")
	public String abc(Model model) {
		List<Product> list = productService.find8Products();
		model.addAttribute("items", list);
		List<Product> all = productService.findByProductModelName("Laptop");
		List<Product> asus = productService.findByProductModelName("Asus");
		List<Product> acer = productService.findByProductModelName("Acer");
		List<Product> msi = productService.findByProductModelName("MSI");
		List<Product> lenovo = productService.findByProductModelName("Lenovo");
		List<Product> dell = productService.findByProductModelName("Dell");
		List<Product> hp = productService.findByProductModelName("HP");
		List<Product> gigabyte = productService.findByProductModelName("Gigabyte");
		List<Product> apple = productService.findByProductModelName("Apple");
		model.addAttribute("all", all);
		model.addAttribute("asus", asus);
		model.addAttribute("acer", acer);
		model.addAttribute("msi", msi);
		model.addAttribute("lenovo", lenovo);
		model.addAttribute("dell", dell);
		model.addAttribute("hp", hp);
		model.addAttribute("gigabyte", gigabyte);
		model.addAttribute("apple", apple);
		return "index";
	}
}

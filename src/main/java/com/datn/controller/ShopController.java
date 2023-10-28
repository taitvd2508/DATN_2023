package com.datn.controller;


import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.datn.model.Product;
import com.datn.model.ProductBrand;
import com.datn.service.ProductBrandService;
import com.datn.service.ProductModelService;
import com.datn.service.ProductService;
import com.datn.service.SessionService;


@Controller
@RequestMapping("/shop")
public class ShopController {
	@Autowired
	ProductService productService;
	@Autowired
	ProductBrandService productBrandService;
	@Autowired
	SessionService session;
	
	@RequestMapping("")
	public String shop(Model model) {
		return "products/shop";
	}
	
	@RequestMapping("/laptop")
	public String shop_laptop(Model model, @RequestParam("trang") Optional<Integer> trang, @RequestParam("brand") Optional<String> brand) {
		Pageable pageable = PageRequest.of(trang.orElse(0), 9);
		Page<Product> laptop = productService.findPageByProductModelName(brand.orElse("Laptop"), pageable);
		model.addAttribute("laptop", laptop);
		List<ProductBrand> productBrands = productBrandService.findAllBrandLaptop();
		model.addAttribute("productBrands", productBrands);
		model.addAttribute("brandd", brand.orElse("Laptop"));
		return "products/shop_laptop";
	}
	
	@RequestMapping("search")
	public String search(Model model, @RequestParam("keywords") Optional<String> keyword, @RequestParam("trang") Optional<Integer> trang) {
		String keywords = keyword.orElse(session.get("keywords","")); // nếu keyword ban đầu chưa có thì gán = rỗng
		session.set("keywords", keywords); // set keyword = 
		Pageable pageable = PageRequest.of(trang.orElse(0), 9);
		Page<Product> laptop = productService.search(keywords, pageable);
		model.addAttribute("laptop", laptop);
		return "products/shop_laptop";
	}
}

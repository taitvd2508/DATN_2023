package com.datn.controller;


import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.datn.model.Product;
import com.datn.model.ProductBrand;
import com.datn.service.ProductBrandService;
import com.datn.service.ProductService;
import com.datn.service.SessionService;


@Controller
public class ShopController {
	@Autowired
	ProductService productService;
	@Autowired
	ProductBrandService productBrandService;
	@Autowired
	SessionService session;
	
	@RequestMapping("/shop/laptop")
	public String shop_laptop(Model model, @RequestParam("trang") Optional<Integer> trang, @RequestParam("brand") Optional<String> brand) {
		Pageable pageable = PageRequest.of(trang.orElse(0), 9);
		Page<Product> laptop = productService.findPageByProductModelName(brand.orElse("Laptop"), pageable);
		model.addAttribute("laptop", laptop);
		List<ProductBrand> productBrands = productBrandService.findAllBrandLaptop();
		model.addAttribute("productBrands", productBrands);
		model.addAttribute("brandd", brand.orElse("Laptop"));
		return "products/shop_laptop";
	}
	
	// CHI TIẾT LAPTOP
	@RequestMapping("/shop/laptop/{id}")
	public String laptop_detail(Model model, @PathVariable("id") Integer id) {
		Product product = productService.findById(id);
		model.addAttribute("product", product);
		return "products/single-product";
	}
	
	@RequestMapping("/shop/linhkien")
	public String shop_linhkien(Model model, @RequestParam("trang") Optional<Integer> trang, @RequestParam("brand") Optional<String> brand) {
		Pageable pageable = PageRequest.of(trang.orElse(0), 9);
		Page<Product> linhkien = productService.findPageByProductModelName(brand.orElse("Linh Kiện"), pageable);
		model.addAttribute("linhkien", linhkien);
		List<ProductBrand> productBrands = productBrandService.findAllBrandLinhKien();
		model.addAttribute("productBrands", productBrands);
		model.addAttribute("brandd", brand.orElse("Linh Kiện"));
		return "products/shop_linhkien";
	}
	// CHI TIẾT LINHKIEN
	@RequestMapping("/shop/linhkien/{id}")
	public String linhkien_detail(Model model, @PathVariable("id") Integer id, @RequestParam("type") Optional<String> type) {
		Product product = productService.findById(id);
		model.addAttribute("product", product);
		return "products/single-product";
	}
	
	@RequestMapping("/shop/search")
	public String search(Model model, @RequestParam("keywords") Optional<String> keyword, @RequestParam("trang") Optional<Integer> trang) {
		String keywords = keyword.orElse(session.get("keywords","")); // nếu keyword ban đầu chưa có thì gán = rỗng
		session.set("keywords", keywords); // set keyword = keywords
		Pageable pageable = PageRequest.of(trang.orElse(0), 9);
		Page<Product> searchProducts = productService.search(keywords, pageable);
		model.addAttribute("searchProducts", searchProducts);
		return "products/shop";
	}
}

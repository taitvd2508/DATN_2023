package com.datn.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.datn.model.Product;

public interface ProductService {
	List<Product> findAll();
	List<Product> findByProductModelName(String productmodel_name);
	Page<Product> findPageByProductModelName(String productmodel_name, Pageable pageable);
	List<Product> find8Products();
	Page<Product> search(String product_name, Pageable pageable);
}

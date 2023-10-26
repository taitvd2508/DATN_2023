package com.datn.service;

import java.util.List;

import com.datn.model.Product;

public interface ProductService {
	List<Product> findAll();
	List<Product> findByProductModelName(String productmodel_name);
	List<Product> find8Products();
}

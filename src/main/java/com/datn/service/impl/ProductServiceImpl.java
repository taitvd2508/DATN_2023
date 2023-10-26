package com.datn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datn.model.Product;
import com.datn.service.ProductService;
import com.datn.dao.ProductDAO;
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDAO productDAO;
	
	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return productDAO.findAll();
	}

	@Override
	public List<Product> findByProductModelName(String productmodel_name) {
		// TODO Auto-generated method stub
		return productDAO.findByProductModelName(productmodel_name);
	}

	@Override
	public List<Product> find8Products() {
		// TODO Auto-generated method stub
		return productDAO.find8Products();
	}

}

package com.datn.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.datn.model.Product;

public interface ProductDAO extends JpaRepository<Product, Integer> {
	@Query("SELECT p FROM Product p WHERE p.productmodel_name LIKE %?1%")
	List<Product> findByProductModelName(String productmodel_name);
	
	@Query(value="SELECT TOP 8 * FROM PRODUCTS ORDER BY Product_price DESC", nativeQuery=true)
	List<Product> find8Products();
}

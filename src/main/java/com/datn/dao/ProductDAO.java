package com.datn.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.datn.model.Product;

public interface ProductDAO extends JpaRepository<Product, Integer> {
	@Query("SELECT p FROM Product p WHERE p.productModel.modelname LIKE %?1%")
	List<Product> findByProductModelName(String modelName);
	
	@Query(value="SELECT TOP 8 * FROM PRODUCTS ORDER BY Product_price DESC", nativeQuery=true)
	List<Product> find8Products();
	
	@Query("SELECT p FROM Product p WHERE p.productModel.modelname LIKE %?1%")
	Page<Product> findPageByProductModelName(String modelName, Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE p.product_name LIKE %?1%")
	Page<Product> search(String product_name, Pageable pageable);
}

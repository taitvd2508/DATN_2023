package com.datn.dao;

import org.springframework.data.jpa.repository.JpaRepository;



import com.datn.model.OrderDetail;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long>{
	
}
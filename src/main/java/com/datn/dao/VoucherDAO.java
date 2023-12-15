package com.datn.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.datn.model.Voucher;

public interface VoucherDAO extends JpaRepository<Voucher, String>{
	
	@Query("SELECT DISTINCT v FROM Voucher v WHERE v.id LIKE ?1")
	Voucher findByIdV(String id);
}

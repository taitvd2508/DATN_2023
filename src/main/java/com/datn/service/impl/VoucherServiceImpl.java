package com.datn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datn.dao.VoucherDAO;
import com.datn.model.Voucher;
import com.datn.service.VoucherService;

@Service
public class VoucherServiceImpl implements VoucherService{
	@Autowired
	VoucherDAO voucherDAO;
	
	@Override
	public List<Voucher> findAll() {
		return voucherDAO.findAll();
	}

	@Override
	public Voucher findByIdV(String idV) {
		return voucherDAO.findByIdV(idV);
	}

	@Override
	public void create(Voucher voucher) {
		voucherDAO.save(voucher);
		
	}

	@Override
	public Voucher update(Voucher voucher) {
		return voucherDAO.save(voucher);
	}

	@Override
	public void delete(String idV) {
		voucherDAO.deleteById(idV);	
	}

}

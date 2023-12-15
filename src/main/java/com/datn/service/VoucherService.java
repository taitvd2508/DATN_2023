package com.datn.service;

import java.util.List;

import com.datn.model.Voucher;

public interface VoucherService {
	List<Voucher> findAll();

	Voucher findByIdV(String idV);
}

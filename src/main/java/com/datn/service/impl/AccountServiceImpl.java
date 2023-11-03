package com.datn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.datn.dao.AccountDAO;
import com.datn.model.Account;
import com.datn.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountDAO accountDAO;

	@Override
	public Account findById(String username) {
		return accountDAO.findById(username).get();
	}
}

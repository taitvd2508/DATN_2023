package com.datn.service;

import java.util.List;

import com.datn.model.Account;

public interface AccountService {
	Account findById(String username);

	List<Account> findAll();
}
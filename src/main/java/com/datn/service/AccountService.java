package com.datn.service;

import com.datn.model.Account;

public interface AccountService {
	Account findById(String username);
}
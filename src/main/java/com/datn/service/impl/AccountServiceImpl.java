package com.datn.service.impl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.datn.dao.AccountDAO;
import com.datn.dao.AuthorityDAO;
import com.datn.dao.RoleDAO;
import com.datn.model.Account;
import com.datn.model.Authority;
import com.datn.model.Order;
import com.datn.model.Role;
import com.datn.service.AccountService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AccountServiceImpl implements AccountService {
	String chuoi = "qwertyu!@#$%^&*_iopasdfghjk!@#$%^&*_l1234567890zxcvbnmQW!@#$%^&*_ERTYUIOP1234567890ASDFGHJKLZXCVBNM!@#$%^&*_";
	int len = 6;
	char[] password = new char[len];
	Random random = new Random();
	
	@Autowired
	AccountDAO accountDAO;
	@Autowired
	RoleDAO roleDAO;
	@Autowired
	AuthorityDAO authorityDAO;

	@Override
	public Account findById(String username) {
		return accountDAO.findById(username).get();
	}

	@Override
	public List<Account> findAll() {
		return accountDAO.findAll();
	}

	@Override
	public Account create(Account account) {
		return accountDAO.save(account);
	}

	@Override
	public Account findByUsername(String username) {
		return accountDAO.findByUsername(username);
	}

	@Override
	public Account resetPassword(String username, String email) {
		Account accountExist = accountDAO.findByUsernameAndEmail(username, email);
		if(accountExist != null) {
			for(int i=0; i<len; i++) {
				password[i] = chuoi.charAt(random.nextInt(chuoi.length()));
			}
			accountExist.setPassword(new String(password)); // gán password mới
			return accountDAO.save(accountExist); // cập nhật lại thông tin
		}
		return null;
	}

	@Override
	public Account autoCreate(Account account) {
			account.setUsername(account.getUsername());
			account.setFullname(account.getUsername());
			account.setEmail(account.getUsername());
			account.setPhoto("user.png");
			account.setActivated(true);
			for(int i=0; i<len; i++) {
				password[i] = chuoi.charAt(random.nextInt(chuoi.length()));
			}
			account.setPassword(new String(password));
			Role role = roleDAO.findById("CUST").get(); // lấy role có id CUST
			Authority authority = new Authority();
			authority.setAccount(account);
			authority.setRole(role);
			accountDAO.save(account);
			authorityDAO.save(authority);
		return null;
	}
}

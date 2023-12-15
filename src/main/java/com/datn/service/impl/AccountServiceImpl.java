package com.datn.service.impl;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import com.datn.dao.AccountDAO;
import com.datn.dao.AuthorityDAO;
import com.datn.dao.RoleDAO;
import com.datn.model.Account;
import com.datn.model.Authority;
import com.datn.model.Role;
import com.datn.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService, UserDetailsService{
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
	@Autowired
	BCryptPasswordEncoder pe;

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

	@Override
	public UserDetails loadUserByUsername(String username) {
		try {
			Account account = accountDAO.findById(username).get();
			String password = account.getPassword();
			String[] roles = account.getAuthorities().stream()
								.map(t -> t.getRole().getId())
								.collect(Collectors.toList()).toArray(new String[0]);
			return User.withUsername(username)
					.password(pe.encode(password))
					.roles(roles).build();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new UsernameNotFoundException(username + " Not Found !");
		}
	}

	@Override
	public void loginFromOAuth2(OAuth2AuthenticationToken oauth2) {
		//String fullname = oauth2.getPrincipal().getAttribute("name"); // laays thong tin tu MXH
		String email = oauth2.getPrincipal().getAttribute("email");
		String password = Long.toHexString(System.currentTimeMillis()); // sinh ra mk ngẫu nhiên từ thời gian hệ thống
				
		UserDetails userDetails = User.withUsername(email)
									  .password(password)
									  .roles("CUST").build();
		Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()); // thế vào userdetails ban đầu
		SecurityContextHolder.getContext().setAuthentication(auth); // THAY THẾ auth vào AUTH của MXH
		// nơi chứa security  // lấy context // set
	}
}

package com.datn.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datn.model.OrderDetailGuest;

public interface OrderDetailGuestDAO extends JpaRepository<OrderDetailGuest, Long> {

}

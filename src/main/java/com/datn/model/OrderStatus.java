package com.datn.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "Order_Status")
public class OrderStatus implements Serializable{
	@Id
	String id;
	String status_name;
}

package com.datn.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "Vouchers")
public class Voucher implements Serializable {
	@Id
	String id;
	Integer discount;
	Double priceapply;
	String descvoucher;
	@Temporal(TemporalType.DATE)
	@Column(name = "Enddate")
	Date endDate = new Date();
	
	@ManyToOne
	@JoinColumn(name = "Productmodel_name")
	ProductModel productModel;
}

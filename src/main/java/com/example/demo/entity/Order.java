package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// 注文ID
	private Integer id; 
	// 顧客ID
	@Column(name = "customer_id")
	private Integer customerId; 
	// 注文日
	@Column(name = "ordered_on")
	private LocalDate orderedOn; 
	// 合計金額
	@Column(name = "total_price")
	private Integer totalPrice; 
	//お届け先
	@Column(name = "delivery_address")
	private String deliveryAddress;
	//電話番号
	@Column(name = "delivery_tel")
	private String deliveryTel;
	
	
	// コンストラクタ
	public Order() {
	}

	public Order(Integer customerId, LocalDate orderedOn, 
			Integer totalPrice, String deliveryAddress,String deliveryTel) {
		this.customerId = customerId;
		this.orderedOn = orderedOn;
		this.totalPrice = totalPrice;
		this.deliveryAddress=deliveryAddress;
		this.deliveryTel=deliveryTel;
	}

	// ゲッター
	public Integer getId() {
		return id;
	}
}

package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// 顧客ID
	private Integer id;
	// 名前
	private String name;
	// 住所
	private String address;
	// 電話番号
	private String tel;
	// メールアドレス
	private String email;
	//パスワード
	private String password;
	//アイコン写真
	@Column(name = "icon_path")
	private String iconPath;

	// コンストラクタ
	public Customer() {
	}
	//ログイン用
	public Customer(String email,String password) {
		this.email = email;
		this.password = password;
	}
	//新規登録用
	public Customer(String name, String email,String address, String tel, String password) {
		this.name = name;
		this.address = address;
		this.tel = tel;
		this.email = email;
		this.password = password;
	}
	//アイコン選択画面用
	public Customer(String iconPath) {
		this.iconPath = iconPath;
	}
	public Customer(String name, String address, String tel, String email, String password, String iconPath) {
		this.name = name;
		this.address = address;
		this.tel = tel;
		this.email = email;
		this.password = password;
		this.iconPath = iconPath;
	}

	// ゲッター
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getTel() {
		return tel;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getIconPath() {
		return iconPath;
	}
	//セッター
	public void setIconPath(String iconPath) {
	    this.iconPath = iconPath;
	}
}

package com.example.demo.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//フィールド
	private Integer id;
	private String name;
	//コンストラクタ
	public Category() {
		
	}
	//ゲッター
	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
//	//セッター
//	public void setId(int id) {
//		this.id = id;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
	

}

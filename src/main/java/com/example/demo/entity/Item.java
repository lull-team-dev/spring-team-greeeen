package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "items")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //主キーの値を自動で設定 するために使われるアノテーション
	//フィールド
	// 商品ID
	private Integer id;
	//役割は 「このフィールドは、データベースの category_id カラムと対応する」 という指定。
	// カテゴリーID
	@Column(name = "category_id")
	private Integer categoryId;
	// 商品名
	private String name;
	// 価格
	private Integer price;
	// 産地
	private String origin;
	// こだわり
	private String feature;
	// ランキング
	private Integer ranking;
	// 画像パス
	@Column(name = "image_path")
	private String imagePath;
	//個数
	@Transient // 永続化対象外
	private Integer quantity; // 数量

	//コンストラクタ
	public Item() {

	}

	//ゲッター
	public Integer getId() {
		return id;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public String getName() {
		return name;
	}

	public Integer getPrice() {
		return price;
	}

	public String getOrigin() {
		return origin;
	}

	public String getFeature() {
		return feature;
	}

	public Integer getRanking() {
		return ranking;
	}

	public String getImagePath() {
		return imagePath;
	}
	public Integer getQuantity() {
		return quantity;
	}
	//セッター
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
//	//セッター
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public void setCategoryId(Integer categoryId) {
//		this.categoryId = categoryId;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public void setPrice(Integer price) {
//		this.price = price;
//	}
//
//	public void setOrigin(String origin) {
//		this.origin = origin;
//	}
//
//	public void setFeature(String feature) {
//		this.feature = feature;
//	}
//
//	public void setRanking(String ranking) {
//		this.ranking = ranking;
//	}
//
//	public void setImagePath(String imagePath) {
//		this.imagePath = imagePath;
//	}

}

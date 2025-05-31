package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	//カテゴリーIDによる検索
	List<Item> findByCategoryId(Integer categoryId);

	//ランキングによる検索
	List<Item> findByRankingOrderByRankingDesc(Integer ranking);


	//名前のあいまい検索（部分一致）
	List<Item> findByNameContaining(String name);
}

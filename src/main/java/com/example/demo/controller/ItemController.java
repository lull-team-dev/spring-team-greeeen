package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Category;
import com.example.demo.entity.Item;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ItemRepository;

import org.springframework.ui.Model; // ✅ これが正解！


@Controller
public class ItemController {

	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	CategoryRepository CategoryRepository;

	// HTMLページ表示
@GetMapping("/items")
public String index(Model model) {
	List<Category> categories = CategoryRepository.findAll();
	model.addAttribute("categories", categories);
	return "items";
}

	// 初回商品取得（非同期 GET）
	@GetMapping("/api/items/all")
	@ResponseBody
	public List<Item> getItemAll() {
		return itemRepository.findAll();
	}

	// カテゴリ検索（非同期 POST）
	@PostMapping("/category/items")
	@ResponseBody
	public List<Item> getItemsByCategory(@RequestBody Map<String, String> request) {
		String categoryIdStr = request.get("categoryId");

		if (categoryIdStr == null || categoryIdStr.isEmpty()) {
			return itemRepository.findAll(); // カテゴリ指定なし→全件返す
		}

		Integer categoryId = Integer.parseInt(categoryIdStr);
		return itemRepository.findByCategoryId(categoryId);
	}
}

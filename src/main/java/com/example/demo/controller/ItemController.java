package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Item;
import com.example.demo.repository.ItemRepository;

@Controller
public class ItemController{
	
	@Autowired
	ItemRepository ItemRepository;
	
	@GetMapping("/item")
	public String  index() {
		 return "items";
	}
	
@PostMapping("/item")
@ResponseBody
public List<Item> getItemsByCategory(@RequestBody Map<String, String> request) {
    String categoryIdStr = request.get("categoryId");

    if (categoryIdStr == null || categoryIdStr.isEmpty()) {
        return ItemRepository.findAll();  // カテゴリ未指定 → 全商品
    }

    Integer categoryId = Integer.parseInt(categoryIdStr);
    return ItemRepository.findByCategoryId(categoryId);
}
}


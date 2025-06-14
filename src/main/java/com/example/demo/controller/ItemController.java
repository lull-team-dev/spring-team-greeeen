package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@PostMapping("/api/items/by-category")
@ResponseBody
public List<Item> getItemsByCategory(@RequestBody Map<String, String> request) {
    String categoryIdStr = request.get("categoryId");

    if (categoryIdStr == null || categoryIdStr.isEmpty()) {
        return ItemRepository.findAll();  // カテゴリ未指定 → 全商品
    }

    Integer categoryId = Integer.parseInt(categoryIdStr);
    return itemRepository.findByCategoryId(categoryId);
}



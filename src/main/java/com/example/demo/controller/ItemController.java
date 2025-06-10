package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



// 非同期じゃないコントローラー
@Controller
public class ItemController {
    
@GetMapping("/items")
public String index() {
	return "items";
}


}

// 非同期用のコントローラー
@RestController
@RequestMapping("/api/items")
public class apiController {

}

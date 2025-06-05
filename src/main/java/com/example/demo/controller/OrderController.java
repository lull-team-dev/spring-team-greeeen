package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class OrderController {
	@Autowired
	HttpSession session;


	// ログイン画面を表示
		@GetMapping("/mypage/order_history")
		public String showOrderHistory() {
			
			return "order_history";
		}
}

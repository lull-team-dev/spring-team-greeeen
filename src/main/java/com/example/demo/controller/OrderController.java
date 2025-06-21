package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class OrderController {
	@Autowired
	HttpSession session;


	//顧客情報入力画面を表示
	@GetMapping("/order")
	public String index(){
		return "customer_form";
	}
	// 注文履歴を表示
		@GetMapping("/mypage/order_history")
		public String showOrderHistory() {
			
			return "order_history";
		}
}

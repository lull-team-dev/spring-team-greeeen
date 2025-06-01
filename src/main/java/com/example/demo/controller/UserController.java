package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	// マイページの表示
	@GetMapping("/mypage")
	public String index() {

		return "mypage";
	}
}

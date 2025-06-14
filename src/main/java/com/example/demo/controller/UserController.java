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

	// 更新画面表示(@GetMapping("/mypage/{id}/update")にしたい)
	@GetMapping("/mypage/update")
	public String update(
	// @PathVariable("id") Integer id, Model model
	) {

		// customerテーブルをID（主キー）で検索
		// Customer item = customerRepository.findById(id).get();
		// model.addAttribute("customer", customer);
		return "update";
	}

	// // 更新処理
	// @PostMapping("/mypage/{id}/update")
	// public String updateAccount(
	// @PathVariable("id") Integer id,
	// @RequestParam(value = "name", defaultValue = "") String name,
	// @RequestParam(value = "address", defaultValue = "") String address,
	// @RequestParam(value = "tel", defaultValue = "") String tel,
	// @RequestParam(value = "email", defaultValue = "") String email,
	// @RequestParam(value = "password", defaultValue = "") String password,
	// @RequestParam(value = "icon_path", defaultValue = "") String icon_path,
	// Model model) {
	//
	// // Customerオブジェクトの生成
	// Customer customer = new Item(id, name, address,tel,email,password,icon_path);
	// // Customerテーブルへの反映（UPDATE）
	// customerRepository.save(customer);
	// // 「/mypage」にGETでリクエストし直す（リダイレクト）
	// return "redirect:/mypage";
	// }
}

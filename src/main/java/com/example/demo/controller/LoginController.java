package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

  // @Autowired
  // HttpSession session;

  // @Autowired
  // Account account;

  // @Autowired
  // CustomerRepository customerRepository;

  // ログイン画面の表示
  @GetMapping("/login")
  public String index() {
    return "login";
  }

  // 新規登録画面の表示
  @GetMapping("/users/new")
  public String create() {
    return "create_account";
  }

  // 推しおにぎり画面の表示
  @GetMapping("/users/icon")
  public String iconSelect() {
    return "create_image";
  }

}

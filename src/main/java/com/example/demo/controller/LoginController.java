package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
@Controller
public class LoginController {

  @Autowired
  HttpSession session;

  // @Autowired
  // Account account;

   @Autowired
  CustomerRepository customerRepository;

  // ログイン画面の表示
  @GetMapping({ "/", "/login", "/logout" })
  public String index() {
    return "login";
  }

  // 新規登録画面の表示
  @GetMapping("/users/new")
  public String create(
		  @RequestParam(name = "error", defaultValue = "") String error,
			Model model) {
		// セッション情報を全てクリアする
		session.invalidate();
		// エラーパラメータのチェック
		if (error.equals("notLoggedIn")) {
			model.addAttribute("message", "全ての項目を入力してください");
		}
    return "create_account";
  }
  
//新規登録の実行処理
@PostMapping("/users/add")
public String add(
       @RequestParam("name") String name,
       @RequestParam("email") String email,
       @RequestParam("address") String address,
       @RequestParam("tel") String tel,
       @RequestParam("password") String password,
       @RequestParam("password_confirm") String password_confirm,
       Model model) {

   // 入力チェックに使うフラグ
   boolean hasError = false;

   // 各入力項目が空かどうかを個別に確認し、エラーメッセージをセット
   if (name.isEmpty()) {
       model.addAttribute("nameMessage", "※名前を入力してください");
       hasError = true;
   }
   if (email.isEmpty()) {
       model.addAttribute("emailMessage", "※メールアドレスを入力してください");
       hasError = true;
   }
   if (address.isEmpty()) {
       model.addAttribute("addressMessage", "※住所を入力してください");
       hasError = true;
   }
   if (tel.isEmpty()) {
       model.addAttribute("telMessage", "※電話番号を入力してください");
       hasError = true;
   }
   if (password.isEmpty()) {
       model.addAttribute("passwordMessage", "※パスワードを入力してください");
       hasError = true;
   }
   if (password_confirm.isEmpty()) {
       model.addAttribute("password_confirmMessage", "※確認用パスワードを入力してください");
       hasError = true;
   }

   // パスワードと確認用パスワードが一致していない場合
   if (!password.isEmpty() && !password_confirm.isEmpty() && !password.equals(password_confirm)) {
       model.addAttribute("message", "※入力されたパスワードと一致しません");
       hasError = true;
   }

   // 何か1つでもエラーがあれば、再度「新規登録画面(create_account)」を表示
   if (hasError) {
       return "create_account";
   }

   // 入力に問題がない場合、Customerエンティティを作成し、DBに保存
   Customer customer = new Customer(name, email, address, tel, password);
   session.setAttribute("customer", customer);

   // 登録完了後、「推しおにぎり登録画面(create_image)」へ遷移
   return "create_image";
}

  // 推しおにぎり画面の表示
  @GetMapping("/users/icon")
  public String iconSelect() {
    return "create_image";
  }

//推しおにぎり画面の登録
  @PostMapping("/users/icon")
  public String select(@RequestParam("onigiri") String iconPath, HttpSession session) {
      Customer customer = (Customer) session.getAttribute("customer");
      if (customer == null) {
          // セッション切れ等の対策
          return "redirect:/users/add";
      }

      customer.setIconPath(iconPath);
      customerRepository.save(customer);

      // セッションから削除（完了後）
      session.removeAttribute("customer");

      return "items"; // 完了画面
  }
}

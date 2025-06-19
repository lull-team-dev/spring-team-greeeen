package com.example.demo.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Customer;
import com.example.demo.model.Account;
import com.example.demo.repository.CustomerRepository;

@Controller
public class LoginController {

	@Autowired
	HttpSession session;

	@Autowired
	Account account;

	@Autowired
	CustomerRepository customerRepository;

	// ログイン画面の表示
	@GetMapping({ "/", "/login", "/logout" })
	public String index(@RequestParam(name = "error", defaultValue = "") String error,
			Model model) {
		// セッション情報を全てクリアする
		session.invalidate();
		//エラーパラメータのチェック
		if (error.equals("notLoggedIn")) {
			model.addAttribute("message", "ログインしてください");
		}
		return "login";
	}

	//ログイン処理
	@PostMapping("/login")
	public String login(
			// フォームから送られてくるメールアドレスを取得
			@RequestParam("email") String email,
			// フォームから送られてくるパスワードを取得
			@RequestParam("password") String password,
			// エラーメッセージなどを画面に渡すためのModel
			Model model,
			// ログイン情報をセッションに保存するために使用
			HttpSession session) {
		// 入力チェック：メールアドレスまたはパスワードが未入力の場合はエラーを返す
		if (email.isEmpty() || password.isEmpty()) {
			model.addAttribute("message", "メールアドレスとパスワードを入力してください");
			return "login";
		}

		// ユーザー認証：メールアドレスとパスワードに一致するユーザーを検索
		List<Customer> customer = customerRepository.findByEmailAndPassword(email, password);
		// 検索結果が空（該当ユーザーが存在しない）場合はエラーメッセージを表示
		if (customer == null) {
			model.addAttribute("message", "アカウントがみつかりません");
			return "login";
		}
		// ログイン成功時：ユーザー情報をセッションに保存（必要に応じて）
		session.setAttribute("customer", customer.get(0)); // 最初の一致ユーザーを保存

		// ログイン後は商品一覧ページなどホーム画面にリダイレクト
		return "redirect:/items";
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

		// 名前のチェック
		if (name.isEmpty()) {
		    model.addAttribute("nameMessage", "※名前を入力してください");
		    hasError = true;
		} else if (name.length() > 50) {
		    model.addAttribute("nameMessage", "※名前は50文字以内で入力してください");
		    hasError = true;
		}
		//メールのチェック  
		if (email.isEmpty()) {
		    model.addAttribute("emailMessage", "※メールアドレスを入力してください");
		    hasError = true;
		} else {
		    Customer existingCustomer = customerRepository.findByEmail(email);
		    if (existingCustomer != null) {
		        model.addAttribute("emailMessage", "※このメールアドレスはすでに使用されています");
		        hasError = true;
		    }
		}
		    
		// 住所のチェック
		if (address.isEmpty()) {
		    model.addAttribute("addressMessage", "※住所を入力してください");
		    hasError = true;
		} else if (address.length() > 255) {
		    model.addAttribute("addressMessage", "※住所は255文字以内で入力してください");
		    hasError = true;
		}
		//電話番号のチェック
		if (tel.isEmpty()) {
			model.addAttribute("telMessage", "※電話番号を入力してください");
			hasError = true;
		}
		//パスワードのチェック
		if (password.isEmpty()) {
			model.addAttribute("passwordMessage", "※パスワードを入力してください");
			hasError = true;
		} else if (password.length() < 8 || password.length() > 32) {
		    model.addAttribute("passwordMessage", "※パスワードは8文字以上32文字以内で入力してください");
		    hasError = true;
		}
		//確認用パスワードのチェック
		if (password_confirm.isEmpty()) {
			model.addAttribute("password_confirmMessage", "※確認用パスワードを入力してください");
			hasError = true;
		}else if (password_confirm.length() < 8 || password_confirm.length() > 32) {
		    model.addAttribute("password_confirmMessage", "※確認用パスワードは8文字以上32文字以内で入力してください");
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

		return "redirect:/login"; // ログイン画面
	}
}

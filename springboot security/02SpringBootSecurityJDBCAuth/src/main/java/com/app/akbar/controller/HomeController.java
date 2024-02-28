package com.app.akbar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping({"/", "/home"})
	public String showHomePage() {
		return "home";
	}
	
	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}
	
	@GetMapping("/admin")
	public String showAdminPage() {
		return "admin";
	}
	
	@GetMapping("/customer")
	public String showCustomerPage() {
		return "customer";
	}
	
	@GetMapping("/hello")
	public String showHelloPage() {
		return "hello";
	}
}

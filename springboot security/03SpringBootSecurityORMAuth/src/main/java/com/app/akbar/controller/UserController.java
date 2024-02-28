package com.app.akbar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.akbar.entity.User;
import com.app.akbar.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService service;
	
	//1. Show User Register Page
	@GetMapping("/register")
	public String showUserRegisterPage() {
		return "UserRegister";
	}
	
	//2. Save User
	@PostMapping("/save")
	public String saveUser(@ModelAttribute User user, Model model) {
		Integer id = service.saveUser(user);
		String message = "User '"+id+"'saved";
		model.addAttribute("message", message);
		return "UserRegister";
	}

}

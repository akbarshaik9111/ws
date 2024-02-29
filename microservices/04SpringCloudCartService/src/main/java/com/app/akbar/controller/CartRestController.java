package com.app.akbar.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartRestController {
	
	@Value("${com.app.title}")
	private String title;
	
	@GetMapping("/info")
	public ResponseEntity<String> showMessage() {
		return ResponseEntity.ok("From Cart Service: "+title);
	}

}

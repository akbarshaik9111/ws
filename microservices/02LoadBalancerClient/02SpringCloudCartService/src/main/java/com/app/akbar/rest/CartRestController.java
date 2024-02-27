package com.app.akbar.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartRestController {
	
	@Value("${server.port}")
	private String port;
	
	@GetMapping("/info")
	public ResponseEntity<String> getCartDetails() {
		return ResponseEntity.ok("From Cart Page: "+port);
	}
}

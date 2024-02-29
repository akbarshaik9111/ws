package com.app.akbar.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.akbar.consumer.CartConsumer;

@RestController
@RequestMapping("/order")
public class OrderRestController {
	
	@Autowired
	private CartConsumer consumer;
	
	public ResponseEntity<String> placeOrder() {
		String msg = consumer.showMessage().getBody();
		return ResponseEntity.ok("From Order Controller ==>"+msg);
	}

}

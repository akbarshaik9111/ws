package com.app.akbar.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.akbar.consumer.CartConsumer;

@RestController
@RequestMapping("/v1/api/order")
public class OrderRestController {
	
	@Autowired
	private CartConsumer consumer;
	 
	@GetMapping("/place")
	public ResponseEntity<String> placeOrder() {
		String cartRes = consumer.getCartResponse();
		return new ResponseEntity<String>("ORDER PLACED WITH ==> "+cartRes, HttpStatus.OK);
	}

}

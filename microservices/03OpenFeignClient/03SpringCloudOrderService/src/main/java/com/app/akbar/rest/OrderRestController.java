package com.app.akbar.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.akbar.consumer.CartConsumer;
import com.app.akbar.entity.Cart;

@RestController
@RequestMapping("/order")
public class OrderRestController {
	
	@Autowired
	private CartConsumer consumer;
	
	@GetMapping("/place")
	public ResponseEntity<String> placeOrder() {
		return ResponseEntity.ok("FROM ORDER CONTROLLER: "+consumer.showMessage().getBody());
	}
	
	@GetMapping("cart/fetch/{id}")
	public ResponseEntity<String> getCartDetails(@PathVariable("id") Integer id) {
		Cart cob = consumer.getCartDetails(id).getBody();
		return ResponseEntity.ok("Order with Data"+cob);
	}
	
	@PostMapping("/cart/add")
	public ResponseEntity<String> addToCart(@RequestBody Cart cart) {
		String addtoCart = consumer.addToCart(cart).getBody();
		return ResponseEntity.ok(addtoCart);
	}

}

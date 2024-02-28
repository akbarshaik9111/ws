package com.app.akbar.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.akbar.entity.Cart;

@RestController
@RequestMapping("/cart")
public class CartRestController {
	
	@Value("${server.port}")
	private String port;
	
	@GetMapping("/info")
	public ResponseEntity<String> showMessage() {
		return ResponseEntity.ok("FROM CART CONTROLLER: "+port);
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<Cart> getCartDetails(@PathVariable("id") Integer id) {
		Cart cart = new Cart();
		cart.setCartId(id);
		cart.setCartCode("ABC");
		cart.setCartPrice(2000.0);
		return ResponseEntity.ok(cart);
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> addToCart(@RequestBody Cart cart) {
		return ResponseEntity.ok("Added to Cart: "+cart);
	}

}

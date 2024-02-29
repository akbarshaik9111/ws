package com.app.akbar.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("Cart-Service")
@Component
public interface CartConsumer {
	
	@GetMapping("/cart/info")
	public ResponseEntity<String> showMessage();

}

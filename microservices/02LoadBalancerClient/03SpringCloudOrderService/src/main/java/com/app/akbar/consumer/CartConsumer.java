package com.app.akbar.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CartConsumer {

	@Autowired
	private LoadBalancerClient client;
	
	public String getCartResponse() {
		// GO TO EUREKA SERVER WITH SERVICE ID
		ServiceInstance si = client.choose("CART-SERVICE");
		
		// READ URI + ADD TO PATH ==> RETURNS URL
		String url = si.getUri()+"/cart/info";
		
		// USE REST TEMPLATE AND CALL
		RestTemplate rt = new RestTemplate();
		
		// MAKE HTTP REQUEST AND GET RESPONSE 
		ResponseEntity<String> response = rt.getForEntity(url, String.class);
		
		// RETURN RESPONSE BODY
		return response.getBody();
	}
}

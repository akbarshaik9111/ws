package com.app.akbar.consumer;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CartConsumer {
	
	// IMPL CLASS IS EurekaDiscoveryClient -- GIVEN BY NETFLIX EUREKA
	@Autowired
	private DiscoveryClient client;
	
	
	public String getCartResponse() {
		// GO TO EUREKA SERVER WITH SERVICE ID
		List<ServiceInstance> list = client.getInstances("CART-SERVICE");
		
		// READ AT INDEX-0 ===> RETURNS SERVICE INSTANCE
		ServiceInstance si = list.get(0);
		
		// READ URI
		URI uri = si.getUri();
		
		// ADD TO PATH ==> RETURNS URL
		String url = uri +"/v1/api/cart/show";
		
		// USE REST TEMPLATE AND CALL
		RestTemplate rt = new RestTemplate();
		
		// MAKE HTTP REQUEST AND GET RESPONSE 
		ResponseEntity<String> response = rt.getForEntity(url, String.class);
		
		// RETURN RESPONSE BODY
		return response.getBody();
	}
}

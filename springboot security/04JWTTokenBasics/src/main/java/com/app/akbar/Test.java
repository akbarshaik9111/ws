package com.app.akbar;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class Test {
	public static void main(String[] args) {
		String secret = "TestAppOneSample";
		
		String token = 
		Jwts.builder()
		.setId("8562455") //user UnqId
		.setSubject("sample") //username
		.setIssuedAt(new Date(System.currentTimeMillis())) //created time
		.setExpiration(new Date(System.currentTimeMillis()+TimeUnit.MINUTES.toMillis(2))) //exp time
		.setIssuer("AKBAR IT") //token provider name
		.signWith(SignatureAlgorithm.HS256, secret.getBytes()) //sec algo, secret
		.compact();
		
		System.out.println(token);
		
		Claims c = Jwts.parser()
		.setSigningKey(secret.getBytes())
		.parseClaimsJws(token).getBody();
		
		System.out.println(c.getIssuer()); //AKBAR IT
		System.out.println(c.getId()); //8562455
		System.out.println(c.getSubject()); //sample
		System.out.println(c.getExpiration()); //Thu Feb 29 09:27:33 IST 2024
		System.out.println(c.getIssuedAt()); //Thu Feb 29 09:25:33 IST 2024
	}
}
package com.app.akbar.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//1. Authorization
	@Bean
	public SecurityFilterChain configurePaths(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				request -> request.antMatchers("/", "/home").permitAll()
				.antMatchers("/admin").hasAuthority("ADMIN")
				.antMatchers("/customer").hasAuthority("CUSTOMER")
				.anyRequest().authenticated()
				)
		.formLogin(form -> form.loginPage("/login").permitAll()
				.defaultSuccessUrl("/hello", true)
				)
		.logout(logout -> logout.permitAll());
		return http.build();
	}
	
	//2. Authentication 
	@Bean
	public UserDetailsService userDetailsService(DataSource datasource) {
		UserDetails user1 = 
		User.withUsername("sam")
			.password("$2a$10$JopAHUzz5.yJvV4QN1jUCOqHcohu8xMaoxVMEKfcKeMvNZieqY3lq")
			.authorities("ADMIN")
			.build();
		
		UserDetails user2 = 
				User.withUsername("ram")
					.password("$2a$10$LJV36w3r3vZdZ0O7SkNqPOY1QLmsrIDvVbgYrbaUv5s1LP/IaZM56")
					.authorities("CUSTOMER")
					.build();
		JdbcUserDetailsManager users = new JdbcUserDetailsManager(datasource);
		users.createUser(user1);
		users.createUser(user2);
		return users;
	}
}

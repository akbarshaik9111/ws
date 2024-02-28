package com.app.akbar.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.akbar.entity.User;
import com.app.akbar.repo.UserReporitory;
import com.app.akbar.service.IUserService;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {
	
	@Autowired
	private UserReporitory repo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public Integer saveUser(User user) {
		String password = passwordEncoder.encode(user.getUserPwd());
		user.setUserPwd(password);
		return repo.save(user).getUserId();
	}

	public Optional<User> getOneUser(Integer id) {
		return repo.findById(id);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> opt = repo.findByUserEmail(username);
		if(opt.isEmpty()) {
			throw new UsernameNotFoundException(username+" Not Found");
		}
		
		User user = opt.get();
		// Before java 8
		/* 
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(String role : user.getUserRoles()) {
			authorities.add(new SimpleGrantedAuthority(role));
		}*/
		
		// Java 8
		List<GrantedAuthority> authorities = user.getUserRoles()
												.stream().map(role -> new SimpleGrantedAuthority(role))
												.collect(Collectors.toList());
		return new org.springframework.security.core.userdetails.User(username, user.getUserPwd(), authorities);
	}
}

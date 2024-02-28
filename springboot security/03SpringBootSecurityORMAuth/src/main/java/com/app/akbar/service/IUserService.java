package com.app.akbar.service;

import java.util.Optional;

import com.app.akbar.entity.User;

public interface IUserService {
	
	Integer saveUser(User user);
	Optional<User> getOneUser(Integer id);

}

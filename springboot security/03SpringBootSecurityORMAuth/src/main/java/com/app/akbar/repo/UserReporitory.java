package com.app.akbar.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.akbar.entity.User;

public interface UserReporitory extends JpaRepository<User, Integer> {
	Optional<User> findByUserEmail(String userEmail);
}

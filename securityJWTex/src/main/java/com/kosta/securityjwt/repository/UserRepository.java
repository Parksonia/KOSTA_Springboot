package com.kosta.securityjwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.securityjwt.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByUsername(String username);
}

package com.sp.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sp.app.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}

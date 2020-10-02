package com.rbc.databanqbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rbc.databanqbackend.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByDid(String did);
	
}

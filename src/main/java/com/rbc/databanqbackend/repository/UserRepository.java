package com.rbc.databanqbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rbc.databanqbackend.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByDeletedFalse();
	User findByDid(String did);
	User findByDeletedFalseAndDid(String did);
	
}

package com.rbc.databanqbackend.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbc.databanqbackend.domain.User;
import com.rbc.databanqbackend.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public User getByDid(String did) {
		return userRepository.findByDid(did);
	}
	@Transactional
	public User save(User user) {
		return userRepository.save(user);
	}
	
}
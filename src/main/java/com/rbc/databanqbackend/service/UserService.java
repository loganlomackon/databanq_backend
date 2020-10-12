package com.rbc.databanqbackend.service;

import java.util.List;

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
	public List<User> getAll() {
		return userRepository.findByDeletedFalse();
	}
	@Transactional
	public User getByDid(String did) {
		return userRepository.findByDeletedFalseAndDid(did);
	}
	@Transactional
	public User save(User user) {
		return userRepository.save(user);
	}
	@Transactional
	public List<User> save(List<User> users) {
		return userRepository.saveAll(users);
	}
	
	public void delete(String did) {
		User user =  getByDid(did);
		user.setDeleted(true);
		user = save(user);
	}
	
}

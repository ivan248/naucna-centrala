package com.udd.naucnacentrala.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udd.naucnacentrala.domain.User;
import com.udd.naucnacentrala.repository.UserRepository;
import com.udd.naucnacentrala.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public User findById(Long id) {
		return userRepository.findById(id).get();
	}
}

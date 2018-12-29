package com.udd.naucnacentrala.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udd.naucnacentrala.domain.User;
import com.udd.naucnacentrala.repository.UserRepository;
import com.udd.naucnacentrala.service.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public boolean signUp(User user) {
		try {
			userRepository.save(user);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}


		
	}

}

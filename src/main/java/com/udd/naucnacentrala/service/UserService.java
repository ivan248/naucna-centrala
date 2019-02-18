package com.udd.naucnacentrala.service;

import com.udd.naucnacentrala.domain.User;

public interface UserService {

	User findByEmail(String email);
	User findById(Long id);

}

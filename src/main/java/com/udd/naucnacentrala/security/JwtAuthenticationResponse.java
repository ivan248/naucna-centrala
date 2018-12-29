package com.udd.naucnacentrala.security;

import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class JwtAuthenticationResponse {
	private final String token;
	@JsonIgnore
	private final UserDetails user;
	private final String message;

	public JwtAuthenticationResponse(String token, UserDetails user, String message) {
		this.token = token;
		this.user = user;
		this.message = message;
	}

	public String getToken() {
		return this.token;
	}

	public UserDetails getUser() {
		return user;
	}

	public String getMessage() {
		return message;
	}
	
}

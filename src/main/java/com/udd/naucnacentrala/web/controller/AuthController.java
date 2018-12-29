package com.udd.naucnacentrala.web.controller;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.udd.naucnacentrala.security.JwtAuthenticationRequest;
import com.udd.naucnacentrala.security.JwtAuthenticationResponse;
import com.udd.naucnacentrala.security.JwtTokenUtil;

@RestController
@RequestMapping(value="/api/authentication")
public class AuthController {
	
	private final Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService myAppUserDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Value("Authorization")
	private String tokenHeader;
	
	@RequestMapping(value="/signin", 
			method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {
		try{
			try {
				final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
						authenticationRequest.getUsername(),
						authenticationRequest.getPassword()));
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
				logger.info("Login succesfull for user: " + authenticationRequest.getUsername());
				
				final UserDetails userDetails = myAppUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
				final String token = jwtTokenUtil.generateToken(userDetails);			
				return ResponseEntity.ok(new JwtAuthenticationResponse(token,userDetails, "Successfull login."));
			} catch(AuthenticationException e) {
				return new ResponseEntity<>(new JwtAuthenticationResponse("", null, "Username or password incorrect."), HttpStatus.FORBIDDEN);
			}
		} catch (BadCredentialsException | UsernameNotFoundException e){
			return new ResponseEntity<>(new JwtAuthenticationResponse("", null, "Bad credentials or username not found."), HttpStatus.FORBIDDEN);
		}
	}

}

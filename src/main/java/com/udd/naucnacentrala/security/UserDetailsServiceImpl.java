package com.udd.naucnacentrala.security;

import java.util.ArrayList;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udd.naucnacentrala.domain.Authority;

@Service("userDetailsService")
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
	private final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private com.udd.naucnacentrala.repository.UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		logger.info("Load user by username: " + username);

		com.udd.naucnacentrala.domain.User userFromDB = userRepository.findByEmail(username);

		if (userFromDB == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		}

		UserDTO user = new UserDTO();

		user.setIsEnabled(true);
		user.setPassword(userFromDB.getPassword());
		user.setUsername(userFromDB.getEmail());
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(Authority a : userFromDB.getAuthorities()) {
			authorities.add(new SimpleGrantedAuthority(a.getName().toString()));
		}
		user.setAuthorities(authorities);
		
		return user;
	}

}

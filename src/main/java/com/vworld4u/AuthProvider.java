package com.vworld4u;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.vworld4u.models.User;
import com.vworld4u.repositories.UserRepository;

@Component
public class AuthProvider implements AuthenticationProvider, UserDetailsService {
	private static final Logger log = LoggerFactory.getLogger(AuthProvider.class);
	
	@Autowired UserRepository userRepository;
	static final List<GrantedAuthority> AUTHORITIES = new ArrayList<>();

	static {
		AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));
	}
	
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String email = (String) auth.getPrincipal();
		String password = (String) auth.getCredentials();
		
		log.info("loginAttempted: " + email + "/" + password);
		User user = userRepository.findByEmail(email);
		log.info("loginAttempted: ExUser = " + user);
		if (user == null || !user.getPassword().equals(password)) {
			throw new BadCredentialsException("Invalid Credentials");
		}

		log.info("loginAttempted: Login Success");
		return new UsernamePasswordAuthenticationToken(user.getEmail(), password, AUTHORITIES);
	}

	@Override
	public boolean supports(Class<?> authenticator) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authenticator);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), null);
		return userDetails;
	}

}

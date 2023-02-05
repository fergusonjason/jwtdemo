package org.hiredgoons.jwtdemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class DemoAuthenticationProvider implements AuthenticationProvider {

	private UserDetailsService userDetailsService;
	
	@Autowired
	public DemoAuthenticationProvider(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String username = authentication.getName();
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		if (userDetails != null) {
			if (userDetails.getPassword().equals(authentication.getCredentials())) {
				authentication.setAuthenticated(true);
			}
		}
		
		return authentication;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication == DemoAuthenticationToken.class;
	}
	
	

}

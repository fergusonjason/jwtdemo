package org.hiredgoons.jwtdemo.security;

import org.springframework.security.core.GrantedAuthority;

public class DemoGrantedAuthority implements GrantedAuthority{
	
	private String authority;

	
	public DemoGrantedAuthority(String authority) {
		super();
		this.authority = authority;
	}


	@Override
	public String getAuthority() {
		return this.authority;
	}

}

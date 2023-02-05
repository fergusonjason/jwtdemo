package org.hiredgoons.jwtdemo.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class DemoUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;

	private List<DemoGrantedAuthority> authorities;
	private String username;
	private String password;
	private boolean active;
	
	public DemoUserDetails(String username, String password, List<DemoGrantedAuthority> authorities, boolean active) {
		
		super();
		this.authorities = authorities;
		this.username = username;
		this.password = password;
		this.active = active;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return active;
	}

	@Override
	public boolean isAccountNonLocked() {
		return active;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return active;
	}

	@Override
	public boolean isEnabled() {
		return active;
	}

}

package org.hiredgoons.jwtdemo.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class DemoAuthenticationToken implements Authentication {

	private static final long serialVersionUID = 1L;

	private String name;
	private String password;
	private List<DemoGrantedAuthority> authorities;
	private DemoUserDetails details;
	private String principal;
	private boolean authenticated;

	public DemoAuthenticationToken() {
		
	}
	
	public DemoAuthenticationToken(String name, String password, List<DemoGrantedAuthority> authorities,
			DemoUserDetails details, String principal, boolean authenticated) {
		super();
		this.name = name;
		this.password = password;
		this.authorities = authorities;
		this.details = details;
		this.principal = principal;
		this.authenticated = authenticated;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAuthorities(List<DemoGrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public void setDetails(DemoUserDetails details) {
		this.details = details;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public Object getCredentials() {
		return password;
	}

	@Override
	public Object getDetails() {
		return details;
	}

	@Override
	public Object getPrincipal() {
		return principal;
	}

	@Override
	public boolean isAuthenticated() {
		return authenticated;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		this.authenticated = isAuthenticated;

	}

}

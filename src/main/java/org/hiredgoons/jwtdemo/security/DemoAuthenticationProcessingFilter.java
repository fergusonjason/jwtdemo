package org.hiredgoons.jwtdemo.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DemoAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {
	
	private static final Logger log = LoggerFactory.getLogger(DemoAuthenticationProcessingFilter.class);

	private static final RequestMatcher requestMatcher = new AntPathRequestMatcher("/doLogin/**");
	
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	public DemoAuthenticationProcessingFilter(AuthenticationManager authManager, AuthenticationSuccessHandler successHandler) {
		super(requestMatcher);
		setAuthenticationManager(authManager);
		setAuthenticationSuccessHandler(successHandler);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		log.debug("Attemping authentication for path {}", request.getServletPath());
		
		String requestBody = IOUtils.toString(request.getReader());
		
		@SuppressWarnings("unchecked")
		Map<String, String> requestBodyMap = objectMapper.readValue(requestBody, HashMap.class);
		String username = requestBodyMap.get("username");
		String password = requestBodyMap.get("password");

		DemoAuthenticationToken auth = new DemoAuthenticationToken();
		auth.setName(username);
		auth.setPassword(password);

		// split this into two lines for breakpoint purposes
		Authentication result = this.getAuthenticationManager().authenticate(auth);
		return result;
	}
	
}

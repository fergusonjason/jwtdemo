package org.hiredgoons.jwtdemo.config;

import java.util.Arrays;

import org.hiredgoons.jwtdemo.security.DemoAuthenticationProcessingFilter;
import org.hiredgoons.jwtdemo.security.DemoAuthenticationProvider;
import org.hiredgoons.jwtdemo.security.DemoGrantedAuthority;
import org.hiredgoons.jwtdemo.security.DemoUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.session.ConcurrentSessionFilter;

@Configuration
public class SecurityConfig {

	
	private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);


	//private AuthenticationConfiguration authConfig;
	
//	@Autowired
//	private DemoAuthenticationProcessingFilter authenticationProcessingFilter;
//	@Autowired
//	private DemoAuthenticationProvider authenticationProvider;
	
	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
//	public AuthenticationSuccessHandler successHandler() {
//		AuthenticationSuccessHandler handler = new DemoAuthenticationSuccessHandler();
//		return handler;
//	}
	
	@Bean
	public DemoAuthenticationProvider authenticationProvider() {
		
		DemoAuthenticationProvider provider = new DemoAuthenticationProvider(userDetailsService());
		return provider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		
		AuthenticationManagerBuilder bob = http.getSharedObject(AuthenticationManagerBuilder.class);
		
		bob.authenticationProvider(authenticationProvider());
		bob.userDetailsService(userDetailsService());
		
		AuthenticationManager authManager = bob.build();
		return authManager;
//		return bob.build();
				
	}	
	
	@Bean
	public AuthenticationEventPublisher authenticationEventPublisher
	        (ApplicationEventPublisher applicationEventPublisher) {
	    return new DefaultAuthenticationEventPublisher(applicationEventPublisher);
	}	
	
	@Bean
	public UserDetailsService userDetailsService() {
		
		DemoUserDetails user = new DemoUserDetails("user", "password", Arrays.asList(new DemoGrantedAuthority("user")), true);
		DemoUserDetails admin = new DemoUserDetails("admin", "admin", Arrays.asList(new DemoGrantedAuthority("admin")), true);
		
		return new InMemoryUserDetailsManager(user, admin);
	}
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		
		// define this in-place so that we don't get a circular dependency by autowiring it.
		//AuthenticationManagerBuilder authBob = http.getSharedObject(AuthenticationManagerBuilder.class);
		DemoAuthenticationProcessingFilter authenticationProcessingFilter = new DemoAuthenticationProcessingFilter(authenticationManager(http),
				authenticationSuccessHandler);
//		authenticationProcessingFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
		
		//@formatter:off
		http
//			.apply(DemoAuthenticationProcessingFilterDsl.demoAuthenticationProcessingFilterDsl())
//			.and()
			.addFilterAfter(authenticationProcessingFilter, ConcurrentSessionFilter.class)
			.authorizeRequests()
				.antMatchers("/doLogin/**", "/error").permitAll()
				.anyRequest().authenticated()
			.and()
			.csrf().disable();
		//@formatter:on
		return http.build();
	}
}

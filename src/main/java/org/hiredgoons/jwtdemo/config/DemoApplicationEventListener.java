package org.hiredgoons.jwtdemo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.security.access.event.AuthorizationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class DemoApplicationEventListener {

	private static final Logger log = LoggerFactory.getLogger(DemoApplicationEventListener.class);

	@EventListener
	public void authenticationSuccess(AuthenticationSuccessEvent event) {

	}

	@EventListener
	public void authenticationFailure(AuthorizationFailureEvent event) {

	}
}

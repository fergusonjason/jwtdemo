package org.hiredgoons.jwtdemo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoApplicationController {

	@PostMapping("/")
	public String helloWorld() {
		
		return "Hello World!";
	}
}

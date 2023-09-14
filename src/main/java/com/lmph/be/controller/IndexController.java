package com.lmph.be.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

	
	@GetMapping("/login")
	public String login(HttpServletRequest request) throws Exception {
				
		return "login";
	}
	
	@GetMapping("/home")
	public String home() {
		
		return "home";
	}
}

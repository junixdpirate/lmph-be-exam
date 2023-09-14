package com.lmph.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lmph.be.entity.User;
import com.lmph.be.service.UserService;

/**
 * User Controller class
 * @author Jhun Tiballa
 */
@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/list")
	@ResponseBody
	public String index() {
		
		User user = new User();
		
		user.setFirstName("Jhun");
		user.setMiddleName("A");
		user.setLastName("Tiballa");
		user.setEmail("jhun.tiballa@legalmatch.com");
		user.setUsername("jhun");
		user.setPassword("test123");
		
		this.userService.insert(user);
		
		return "Hello Users";
	}
	
}

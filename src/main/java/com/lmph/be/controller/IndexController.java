package com.lmph.be.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.lmph.be.enums.Gender;
import com.lmph.be.enums.MaritalStatus;
import com.lmph.be.form.EmployeeForm;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Index Controller class
 * @author Jhun Tiballa
 */
@Controller
public class IndexController {

	private boolean isAuthenticated() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication == null || AnonymousAuthenticationToken.class.
	      isAssignableFrom(authentication.getClass())) {
	        return false;
	    }
	    return authentication.isAuthenticated();
	}
	
	@GetMapping("/login")
	public String login() throws Exception {
				
		if(this.isAuthenticated()) {
			return "redirect:/home";
		}
		
		return "login";
	}
	
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
	@GetMapping("/employees")
	public String index() {
		return "employees";
	}
	
	@SuppressWarnings("unused")
	@GetMapping("/employees/add")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String form(@ModelAttribute("form") EmployeeForm form) {
		return "employee_form";
	}
	
	@GetMapping("/employees/{id}")
	public String form(@PathVariable Long id, @ModelAttribute("form") EmployeeForm form) {
		form.setId(id);		
		return "employee_form";
	}
}

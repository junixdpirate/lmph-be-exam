package com.lmph.be.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lmph.be.dto.EmployeeInfo;
import com.lmph.be.form.EmployeeForm;
import com.lmph.be.service.EmployeeService;

import jakarta.validation.Valid;

/**
 * User Controller class
 * @author Jhun Tiballa
 */
@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@QueryMapping
	public EmployeeInfo employeeById(@Argument Long id) {
		return this.employeeService.getEmployee( id );			
	}
		
	@QueryMapping
	public List<EmployeeInfo> employees() {
		return this.employeeService.getEmployees();
	}
	
	@PostMapping("/employees/post")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String upsert(@Valid @ModelAttribute("form") EmployeeForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
						
		if (bindingResult.hasErrors()) {	
			return "employee_form";			
		}
		else {						
			this.employeeService.upsert(form);			
			redirectAttributes.addFlashAttribute("info", List.of("employee.upsert.success"));
		}
			
		return "redirect:/employees";
	}
	
	@DeleteMapping("/employees/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ResponseBody
	public void delete(@PathVariable Long id) {
		this.employeeService.delete(id);		
	}
}

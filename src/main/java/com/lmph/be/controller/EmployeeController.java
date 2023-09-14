package com.lmph.be.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.lmph.be.entity.Employee;
import com.lmph.be.service.EmployeeService;

/**
 * User Controller class
 * @author Jhun Tiballa
 */
@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@QueryMapping
	public Employee employeeById(@Argument Long id) {
		
		return this.employeeService.getEmployee( id );
	}
	
	@QueryMapping
	public List<Employee> employees() {
		return this.employeeService.getEmployees();
	}
}

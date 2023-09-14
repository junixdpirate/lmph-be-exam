package com.lmph.be.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lmph.be.dao.EmployeeDao;
import com.lmph.be.entity.Address;
import com.lmph.be.entity.Contact;
import com.lmph.be.entity.Employee;

/**
 * User Service class
 * 
 * @author Jhun Tiballa
 */
@Service
public class EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	
	public Employee insert(Employee employee, List<Address> addresses, List<Contact> contacts) {
				
		employee.getAddresses().addAll(addresses);
		employee.getContacts().addAll(contacts);
		
		return this.employeeDao.save(employee);

	}
	
	public Employee update(Employee employee) {
				
		return this.employeeDao.save(employee);
	}
	
	public void delete(Long id) {
		
		this.employeeDao.deleteById(id);
	}
	
	public Employee getEmployee(Long userId) {		
		try {
			return this.employeeDao.findById(userId).get();
		}
		catch(NoSuchElementException e) {
			return null;
		}
	}
		
	public List<Employee> getEmployees() {		
		return (List<Employee>) this.employeeDao.findAll();		
	}
}

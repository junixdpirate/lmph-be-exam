package com.lmph.be.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lmph.be.entity.Address;
import com.lmph.be.entity.Contact;
import com.lmph.be.entity.Employee;

/**
 * User Service Unit Test
 * @author Jhun Tiballa
 */
@SpringBootTest
class EmployeeServiceTest {

	@Autowired
	private EmployeeService employeeService;
	
	@Test
	public void insertEmployeeSuccess() {
		
		Employee employee = new Employee();
		
		employee.setFirstName("Jane");
		employee.setLastName("Doe");
		
		
		Contact contact = new Contact();
		contact.setContactNo("11111");
		contact.setIsPrimary(true);
		contact.setEmployee(employee);
		
		List<Address> addresses = new ArrayList<>();
		Address address1 = new Address();
		address1.setAddress1("123 street");
		address1.setAddress2("nice city");
		address1.setIsPrimary(true);
		address1.setEmployee(employee);
		
		addresses.add(address1);
		
		Address address2 = new Address();
		address2.setIsPrimary(false);
		address2.setAddress2("lapulapu city");
		address2.setEmployee(employee);
		
		addresses.add(address2);
		
		
		//employee = this.employeeService.insert(employee, addresses, List.of(contact));
		
		//System.out.println(employee);
		
		assertThat(employee.getId()).isNotNull();
	}
	
	@Test
	public void getEmployeeReturnsEmployeeEntity() {
		
		Employee employee = this.employeeService.getEmployee(16L);
		
		
		assertThat(employee).isInstanceOf(Employee.class);
		assertThat(employee.getId()).isEqualTo(16L);
		
		assertThat(employee.getAddresses()).isNotEmpty();
		assertThat(employee.getContacts()).isNotEmpty();
		
		System.out.println( "addresses --- ");
		employee.getAddresses().forEach( addr -> {
			System.out.println( addr.getId() );
			System.out.println( addr.getAddress1() );
			
		});
		
		System.out.println( "contacts --- ");
		employee.getContacts().forEach( contact -> {
			System.out.println( contact.getId() );
			System.out.println( contact.getContactNo() );
		});
		//System.out.print(employee.getAddresses().toString());
		//System.out.print(employee.getContacts().toString());
	}
	
	@Test
	public void updateEmployeeRemovedAllContactsAndAddressesSuccess() {
		
		Employee employee = this.employeeService.getEmployee(16L);
		
		employee.setAddresses(new ArrayList<>());
		employee.setContacts(new ArrayList<>());
		
		//employee = this.employeeService.update(employee);
		
		assertThat(employee).isInstanceOf(Employee.class);
		assertThat(employee.getId()).isEqualTo(16L);
		
		assertThat(employee.getAddresses()).isEmpty();
		assertThat(employee.getContacts()).isEmpty();
	}
	
	@Test
	public void updateEmployeeAddContactsAndAddressSuccess() {
		
		Employee employee = this.employeeService.getEmployee(16L);
		
		employee.setFirstName("Jane-edited");
		employee.setLastName("Doe-edited");
		
		
		Contact contact = new Contact();
		contact.setContactNo("11111-2222");
		contact.setIsPrimary(true);
		contact.setEmployee(employee);
		
		List<Address> addresses = new ArrayList<>();
		Address address1 = new Address();
		address1.setAddress1("123 street -edited");
		address1.setAddress2("nice city - edited");
		address1.setIsPrimary(true);
		address1.setEmployee(employee);
		
		addresses.add(address1);
		
		Address address2 = new Address();
		address2.setIsPrimary(false);
		address2.setAddress2("lapulapu city - edited");
		address2.setEmployee(employee);
		
		addresses.add(address2);
		
		employee.getAddresses().addAll(addresses);
		employee.getContacts().addAll(List.of(contact));
		
		//employee = this.employeeService.update(employee);
		
		//System.out.println(employee);
		
		assertThat(employee.getId()).isNotNull();
	}
	
	@Test
	public void updateEmployeeUpdateContactsAndAddressSuccess() {
		
		Employee employee = this.employeeService.getEmployee(16L);
		
		for( int i = 0; i < employee.getAddresses().size(); i++ ) {
			
			employee.getAddresses().get(i).setAddress1( "edited address a");
		}
		
		for( int i = 0; i < employee.getContacts().size(); i++ ) {
			
			employee.getContacts().get(i).setContactNo("555-55555");
		}
		
		//employee = this.employeeService.update(employee);
		
		assertThat(employee.getId()).isNotNull();
	}
	
	@Test
	public void deleteEmployeeSuccess() {
		
		this.employeeService.delete(17L);
		
		Employee employee = this.employeeService.getEmployee(17L);
		
		assertThat(employee).isNull();
	}

}

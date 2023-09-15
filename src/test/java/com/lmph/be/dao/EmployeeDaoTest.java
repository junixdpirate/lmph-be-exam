package com.lmph.be.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lmph.be.entity.Employee;

/**
 * User DAO Unit Test
 * @author Jhun Tiballa
 */
@SpringBootTest
class EmployeeDaoTest {

	@Autowired
	private EmployeeDao employeeDao;
		
	
	@Test
	public void insertSuccess() {
				
		Employee user = new Employee();
		
		user.setFirstName("Jhun");
		user.setMiddleName("A");
		user.setLastName("Tiballa");
		
		
	
		user = this.employeeDao.save(user);
		
		System.out.print(user);
		
		
		assertThat(user.getId()).isNotNull();
	}
	
	@Test
	public void findByIdReturnsUserEntity() {
		
		Employee user = this.employeeDao.findById(3L).get();
		
		assertThat(user).isNotNull();
		
	}
	
	@Test
	public void updateSuccess() {
		
		Employee user = this.employeeDao.findById(3L).get();
		
		Long userId = user.getId();
		
		user.setFirstName("Jon");		
		
		
	
		user = this.employeeDao.save(user);
		
		System.out.print(user);
		
		
		assertThat(user.getId()).isEqualTo(userId);
		assertThat(user.getFirstName()).isEqualTo("Jon");
		
		
	}
	
	@Test
	public void deleteByIdSuccess() {
		
		Long userId = 1L;
		Employee user = null;
		
		try {
			
			user = this.employeeDao.findById(userId).get();
			
			this.employeeDao.deleteById(user.getId());
		
			user = this.employeeDao.findById(userId).get();
		}
		catch(NoSuchElementException e) {
			user = null;
		}
		
		assertThat(user).isNull();
	}

}

package com.lmph.be.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lmph.be.entity.User;
import com.lmph.be.service.UserService;

/**
 * User DAO Unit Test
 * @author Jhun Tiballa
 */
@SpringBootTest
class UserDaoTest {

	@Autowired
	private UserDao userDao;
		
	
	@Test
	public void insertSuccess() {
				
		User user = new User();
		
		user.setFirstName("Jhun");
		user.setMiddleName("A");
		user.setLastName("Tiballa");
		user.setEmail("jhun.tiballa@legalmatch.com");
		user.setUsername("jhun");
		user.setPassword("test123");
		
	
		user = this.userDao.save(user);
		
		System.out.print(user);
		
		
		assertThat(user.getId()).isNotNull();
	}
	
	@Test
	public void findByIdReturnsUserEntity() {
		
		User user = this.userDao.findById(3L).get();
		
		assertThat(user).isNotNull();
		assertThat(user.getEmail()).isEqualTo("jhun.tiballa@legalmatch.com");
	}
	
	@Test
	public void updateSuccess() {
		
		User user = this.userDao.findById(3L).get();
		
		Long userId = user.getId();
		
		user.setFirstName("Jon");		
		user.setUsername("jon");
		user.setPassword("test231");
		
	
		user = this.userDao.save(user);
		
		System.out.print(user);
		
		
		assertThat(user.getId()).isEqualTo(userId);
		assertThat(user.getFirstName()).isEqualTo("Jon");
		assertThat(user.getUsername()).isEqualTo("jon");
		assertThat(user.getPassword()).isEqualTo("test231");
		
	}
	
	@Test
	public void deleteByIdSuccess() {
		
		Long userId = 1L;
		User user = null;
		
		try {
			
			user = this.userDao.findById(userId).get();
			
			this.userDao.deleteById(user.getId());
		
			user = this.userDao.findById(userId).get();
		}
		catch(NoSuchElementException e) {
			user = null;
		}
		
		assertThat(user).isNull();
	}

}

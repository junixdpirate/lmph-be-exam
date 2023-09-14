package com.lmph.be.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.lmph.be.dao.UserDao;
import com.lmph.be.dto.UserList;
import com.lmph.be.entity.User;

/**
 * User Service class
 * 
 * @author Jhun Tiballa
 */
@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public User insert(User user) {
				
		return this.userDao.save(user);
	}
	
	public User update(User user) {
		
		return this.userDao.save(user);
	}
	
	public void delete(Long userId) {
		
		this.userDao.deleteById(userId);
	}
	
	public User getUser(Long userId) {		
		try {
			return this.userDao.findById(userId).get();
		}
		catch(NoSuchElementException e) {
			return null;
		}
	}
	
	public User getUser(String email) {
		return this.userDao.getUserByEmail(email);
	}
	
	public List<UserList> getUsers() {
		
		List<User> users = (List<User>) this.userDao.findAll();
		
		List<UserList> list = new ArrayList<>();
		
		return list;
	}
}

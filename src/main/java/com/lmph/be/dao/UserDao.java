package com.lmph.be.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lmph.be.entity.User;

/**
 * 
 * @author Jhun Tiballa
 */
@Repository
public interface UserDao extends JpaRepository<User, Long>{
	
	public User getUserByEmail(String email);
	
}

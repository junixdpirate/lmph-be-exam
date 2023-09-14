package com.lmph.be.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lmph.be.entity.Employee;

/**
 * 
 * @author Jhun Tiballa
 */
public interface EmployeeDao extends JpaRepository<Employee, Long>{

}

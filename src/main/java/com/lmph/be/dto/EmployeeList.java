package com.lmph.be.dto;

import java.io.Serializable;

import com.lmph.be.entity.Employee;

import lombok.Data;

/**
 * @author User
 */
@Data
public class EmployeeList extends Employee implements Serializable {

	private static final long serialVersionUID = -6179502637213304102L;

}

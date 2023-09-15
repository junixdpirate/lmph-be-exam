package com.lmph.be.dto;

import java.time.LocalDate;
import java.time.Period;

import com.lmph.be.entity.Employee;

/**
 * Employee Info DTO
 * @author Jhun Tiballa
 */
public class EmployeeInfo extends Employee  {
	
	public Integer getAge() {
		
		if(  this.getBirthdate() != null ) {
			Period period = Period.between(LocalDate.now(), this.getBirthdate());
			return Math.abs(period.getYears());
		}
		
		return 0;
	}
	
	public Integer getYearsOfExperience() {
		if(  this.getDateHired() != null ) {
			Period period = Period.between(LocalDate.now(), this.getDateHired());
			return Math.abs(period.getYears());
		}
		
		return 0;
	}

}

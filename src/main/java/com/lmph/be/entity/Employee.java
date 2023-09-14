package com.lmph.be.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import com.lmph.be.enums.Gender;
import com.lmph.be.enums.MaritalStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Entity User class
 * @author Jhun Tiballa
 */
@Data
@Entity
@Table(name = "employees")
public class Employee {

	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
			
	private String firstName;
	
	private String lastName;
	
	private String middleName;
	
	private String email;
	
	private Date birthdate;
	
	private Gender gender;
	
	private MaritalStatus maritalStatus;
	
	private String companyPosition;
	
	private Date dateHired;
	
	@OneToMany(mappedBy="employee", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Contact> contacts = new ArrayList<>();
	
	@OneToMany(mappedBy="employee", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Address> addresses = new ArrayList<>();
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
}
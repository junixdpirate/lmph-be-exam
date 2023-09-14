package com.lmph.be.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;



import com.lmph.be.enums.Gender;
import com.lmph.be.enums.MaritalStatus;
import com.lmph.be.vo.AddressInfo;
import com.lmph.be.vo.ContactInfo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Entity User class
 * @author Jhun Tiballa
 */
@Data
@Entity
@Table(name = "users")
public class User {

	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String username;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private String middleName;
	
	private String email;
	
	private Date birthdate;
	
	private Gender gender;
	
	private MaritalStatus maritalStatus;
	
	private String companyPosition;
	
	private Date dateHired;
	
	//private List<ContactInfo> contactInfoList;
	
	//private List<AddressInfo> addressInfoList;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
}

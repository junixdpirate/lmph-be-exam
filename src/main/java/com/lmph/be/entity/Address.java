package com.lmph.be.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Address Entity
 * @author Jhun Tiballa
 */
@Data
@Entity
@Table(name = "addresses")
public class Address {

	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
			
	private String address1;
	
	private String address2;
	
	private Boolean isPrimary;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="employee_id")
	private Employee employee;
}
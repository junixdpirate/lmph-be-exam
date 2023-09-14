package com.lmph.be.vo;

import lombok.Data;

/**
 * Contact Info class
 * @author Jhun Tiballa
 */
@Data
public class ContactInfo {

	/**
	 * Phone Number
	 */
	private String phoneNo;
	
	/**
	 * Is Primary Phone Number
	 */
	private Boolean isPrimary;
}

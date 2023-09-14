package com.lmph.be.vo;

import lombok.Data;

/**
 * Address Info class
 * @author Jhun Tiballa
 */
@Data
public class AddressInfo {
	
	/**
	 * Address 1st Line
	 */
	private String address1;
	
	/**
	 * Address 2nd line
	 */
	private String address2;
	
	/**
	 * Is primary address
	 */
	private Boolean isPrimary;
}

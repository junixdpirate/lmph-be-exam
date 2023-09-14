package com.lmph.be.dto;

import java.io.Serializable;

import com.lmph.be.entity.User;
import com.lmph.be.enums.Gender;
import com.lmph.be.enums.MaritalStatus;

import lombok.Data;

/**
 * User Detail DTO
 * @author Jhun Tiballa
 */
@Data
public class UserDetail extends User implements Serializable {

	private static final long serialVersionUID = -2252548455196595678L;

	/**
	 * Genders
	 */
	private Gender genders;
	
	/**
	 * Marital Statues
	 */
	private MaritalStatus maritalStatuses;
}

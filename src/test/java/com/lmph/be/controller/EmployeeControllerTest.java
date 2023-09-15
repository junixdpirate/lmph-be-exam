package com.lmph.be.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.servlet.ModelAndView;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	
	@Test
	public void upsertSuccess() throws Exception {
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		
		params.add("id", "");
		params.add("firstName", "Juan");
		params.add("lastName", "Dela Cruz");
		params.add("middleName", "M");
		params.add("birthdate", "1990-01-01");
		params.add("companyPostion", "Employee");
		params.add("dateHired", "2010-01-01");
		params.add("maritalStatus", "M");
		params.add("gender", "M");
				
		RequestBuilder request = MockMvcRequestBuilders.post("/employees/post").params(params);

		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(302));
						
	}
}

package com.lmph.be.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.lmph.be.form.EmployeeForm;
import com.lmph.be.service.EmployeeService;

/**
 * Employee Controller Unit Test
 * @author Jhun Tiballa
 */
@SpringBootTest
@AutoConfigureMockMvc
//@GraphQlTest(EmployeeController.class)
class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EmployeeService employeeService;
	
	@Test
	@WithMockUser(username = "user", roles = {"USER"})
	public void upsert_hasUserRoleStatusForbidden() throws Exception {
						
		RequestBuilder request = MockMvcRequestBuilders.post("/employees/post");
		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(403));
	}
	
	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void upsert_withValidationReturnsValidationErrorsAndForm() throws Exception {
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		
		RequestBuilder request = MockMvcRequestBuilders.post("/employees/post").params(params);

		mockMvc.perform(request)
								.andExpect(MockMvcResultMatchers.status().isOk())
								.andExpect(MockMvcResultMatchers.view().name("employee_form"))								
								.andExpect(MockMvcResultMatchers.model().hasErrors())
								.andReturn();
		
		EmployeeForm actual = new EmployeeForm();
		verify( this.employeeService, Mockito.never()).upsert( actual );
	}
	
	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void upsert_successRedirectToEmployees() throws Exception {
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		
		params.add("id", "");
		params.add("firstName", "Juan");
		params.add("lastName", "Dela Cruz");
		params.add("middleName", "M");
		params.add("birthdate", "1990-01-01");
		params.add("companyPosition", "Employee");
		params.add("dateHired", "2010-01-01");
		params.add("maritalStatus", "M");
		params.add("gender", "M");
		
		when(this.employeeService.upsert(Mockito.any())).thenReturn(Mockito.any());	
		
		RequestBuilder request = MockMvcRequestBuilders.post("/employees/post").params(params);
		mockMvc.perform(request)
								.andExpect(MockMvcResultMatchers.status().is(302))
								.andExpect(redirectedUrl("/employees"))																
								.andExpect(MockMvcResultMatchers.model().hasNoErrors())
								.andReturn();
		
		EmployeeForm actual = new EmployeeForm();	
		actual.setFirstName("Juan");
		actual.setLastName("Dela Cruz");
		actual.setMiddleName("M");
		actual.setBirthdate( LocalDate.parse("1990-01-01") );
		actual.setGender("M");
		actual.setMaritalStatus("M");
		actual.setCompanyPosition("Employee");
		actual.setDateHired( LocalDate.parse("2010-01-01") );
		
		verify( this.employeeService ).upsert( actual );
	}
	
	@Test
	@WithMockUser(username = "user", roles = {"USER"})
	public void delete_hasUserRoleStatusForbidden() throws Exception {
		
		RequestBuilder request = MockMvcRequestBuilders.delete("/employees/1");
		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(403));
	}
	
	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void delete_success() throws Exception {
						
		RequestBuilder request = MockMvcRequestBuilders.delete("/employees/1");
		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
								
		verify( this.employeeService ).delete(1L);
	}
}

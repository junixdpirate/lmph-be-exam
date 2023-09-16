package com.lmph.be.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Index Controller Unit Test
 * @author Jhun Tiballa
 */
@SpringBootTest
@AutoConfigureMockMvc
class IndexControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void login_unAuthenticatedStatusOk() throws Exception {
		
		RequestBuilder request = MockMvcRequestBuilders.get("/login");
		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	@WithMockUser
	public void login_authenticatedRedirectToHomePage() throws Exception {
		
		RequestBuilder request = MockMvcRequestBuilders.get("/login");
		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(302))
								.andExpect(redirectedUrl("/home"));
	}
	
	@Test
	public void home_unAuthenticatedRedirectToLogin() throws Exception {
		
		RequestBuilder request = MockMvcRequestBuilders.get("/home");
		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(302))
								.andExpect(redirectedUrlPattern("**/login"));
		
	}
	
	@Test
	@WithMockUser
	public void home_authenticatedStatusOk() throws Exception {
		
		RequestBuilder request = MockMvcRequestBuilders.get("/home");
		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
								
		
	}
	
	@Test
	public void index_unAuthenticatedRedirectToLogin() throws Exception {
		
		RequestBuilder request = MockMvcRequestBuilders.get("/employees");
		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(302))
		.andExpect(redirectedUrlPattern("**/login"));
	}
	
	@Test
	@WithMockUser
	public void index_authenticatedStatusOk() throws Exception {
		
		RequestBuilder request = MockMvcRequestBuilders.get("/employees");
		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	
	@ParameterizedTest
	@ValueSource(strings = { "add", "1" })
	public void form_unAuthenticatedRedirectToLogin(String urlPart ) throws Exception {
		
		RequestBuilder request = MockMvcRequestBuilders.get("/employees/" + urlPart);
		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(302))
		.andExpect(redirectedUrlPattern("**/login"));
	}
	
	@Test
	@WithMockUser(username = "user", roles = {"USER"})
	public void form_AddPageHasUserRoleStatusForbidden() throws Exception {
		
		RequestBuilder request = MockMvcRequestBuilders.get("/employees/add");
		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(403));
	}
	
	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	public void form_AddPageHasAdminRoleStatusOk() throws Exception {
		
		RequestBuilder request = MockMvcRequestBuilders.get("/employees/add");
		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	@WithMockUser()
	public void form_EditOrViewPageAuthenticatedStatusOk() throws Exception {
		
		RequestBuilder request = MockMvcRequestBuilders.get("/employees/1");
		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
	}
}

package com.lmph.be.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.formLogin();
		http.authorizeHttpRequests().anyRequest().authenticated();
		return http.build();
	}
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
	  return new BCryptPasswordEncoder();
	}
	
	@Bean
	UserDetailsService userDetailsService() {
	  
	  UserDetails user = User.builder()
				.username("user")
				.password(passwordEncoder().encode("lmph"))
				.roles("USER")
				.build();
	  UserDetails admin = User.builder()
				.username("admin")
				.password(passwordEncoder().encode("lmph"))
				.roles("USER", "ADMIN")
				.build();
			
	  //UserDetails user = User.withUsername("javainuse").password(passwordEncoder().encode("javainuse"))
	  //  .authorities("read").build();

	  return new InMemoryUserDetailsManager(user, admin);
	 }

}

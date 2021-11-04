package com.kokabmedia.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/*
 * This class is a way to automatically configure a Spring application based on the 
 * dependencies that are present on the class path. This can make development faster
 * and easier by eliminating the need for defining certain beans that are included 
 * in the auto-configuration classes.
 * 
 * The @Configuration declares one or more @Bean methods and may be processed by the
 * Spring container to generate bean definitions and service requests for those beans 
 * at runtime
 */
@Configuration
@EnableWebSecurity // Enables web security 
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication().withUser("Ghiam").password("password")
				.roles("USER", "ADMIN");
	}

	// Ad exclusion to certain URL of the security protocols 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/login").permitAll()
				.antMatchers("/", "/*todo*/**").access("hasRole('USER')").and()
				.formLogin();
	}
}


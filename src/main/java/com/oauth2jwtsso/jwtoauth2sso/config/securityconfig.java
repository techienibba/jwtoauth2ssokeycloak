package com.oauth2jwtsso.jwtoauth2sso.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class securityconfig {
	
	
	
	@Autowired
	JwtAuthConverter jwtauthconverter;
	
	
	
	@Bean
	public SecurityFilterChain securityfilterchain(HttpSecurity http) throws Exception
	{
		
		
		http.
		authorizeHttpRequests(authorize ->authorize.requestMatchers("/Admin").hasRole("ADMIN").requestMatchers("/User").hasRole("USER").anyRequest().authenticated())
		
		.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtauthconverter)));
		 
		 return http.build();
		
		
		
		
		
		
		
	}
	
	

}

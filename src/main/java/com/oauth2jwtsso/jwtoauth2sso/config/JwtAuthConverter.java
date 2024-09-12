package com.oauth2jwtsso.jwtoauth2sso.config;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.management.relation.Role;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;
@Component
public class JwtAuthConverter implements Converter<Jwt,AbstractAuthenticationToken> {
	
	private final JwtGrantedAuthoritiesConverter jwtconverter = new JwtGrantedAuthoritiesConverter();

	@Override
	public AbstractAuthenticationToken convert(Jwt jwt) {
		
		Collection<GrantedAuthority> authorities = Stream.concat(jwtconverter.convert(jwt).stream(), extractclaims(jwt).stream()).collect(Collectors.toSet());
		
		return new JwtAuthenticationToken(jwt,authorities);
	}
	
	private Collection<? extends GrantedAuthority> extractclaims(Jwt jwt)
	{
		Set<String> roles = new HashSet();
		
		Map<String,Object> realmaccess = jwt.getClaim("realm_access");
		if(realmaccess!=null && realmaccess.containsKey("role"))
		{
			
			roles.addAll((Collection<? extends String>) realmaccess.get("roles"));
		}
		
		

		Map<String,Object> resourceaccess = jwt.getClaim("resource_access");
		if(resourceaccess != null && resourceaccess.containsKey("demo"))
		{
			Map<String,Object> demoaccess = (Map<String, Object>) resourceaccess.get("demo");
			if(demoaccess!=null && demoaccess.containsKey("roles"))
			{
				
				roles.addAll((Collection<?extends String>) demoaccess.get("roles"));
			}
		}
		System.out.println("The roles are:"+ roles);
		return roles.stream().map(role ->new SimpleGrantedAuthority("ROLE_" + role.toUpperCase())).collect(Collectors.toSet());
		
			
		}
		
	}
	
	



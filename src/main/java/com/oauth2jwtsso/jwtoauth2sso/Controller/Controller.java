package com.oauth2jwtsso.jwtoauth2sso.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	
	
	
	@GetMapping("/User")
	public String getuser()
	{
		
		return "Hi,User";
	}
	
	
	@GetMapping("/Admin")
	public String getadmin()
	{
		
		return "Hi,Admin";
	}
	
	

}

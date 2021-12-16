package com.ketul.resource;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ketul.module.TokenConfirmation;
import com.ketul.repo.UserRepo;
import com.ketul.service.MailService;
import com.ketul.service.MyUserDetailsService;
import com.ketul.service.TokenService;

@RestController
public class MailResource {
	
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private TokenService tokenService;
	
	@GetMapping("/conform/{token}")
	public String Conform(@PathVariable String token) {
		
		System.out.println(token);
		
		TokenConfirmation tokenConfirmation = tokenService.geTokenConfirmation(token);
		
		if(tokenConfirmation == null) {
			return "Please Logine again with provided mail id";
		}
		
		if(LocalDateTime.now().isEqual(tokenConfirmation.getExpiresAt()) || LocalDateTime.now().isAfter(tokenConfirmation.getExpiresAt())) {
			
			System.out.println(tokenService.deleteToken(token));
			System.out.println(myUserDetailsService.deleteUser(tokenConfirmation.getUser().getId()));
			
			return "Your Token is Expire";
		}
		
		myUserDetailsService.setEnable(tokenConfirmation.getUser().getId());
		tokenService.setEnable(tokenConfirmation.getTokenId());
		
		return "Conformed";
	}
}

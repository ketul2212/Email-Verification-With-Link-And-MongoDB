package com.ketul.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ketul.module.TokenConfirmation;
import com.ketul.module.User;
import com.ketul.repo.TokenRepo;

@Service
public class TokenService {
	
	@Autowired
	private TokenRepo tokenRepo;
	
	public TokenConfirmation geTokenConfirmation(String token) {
		return tokenRepo.findByToken(token);
	}
	
	public String deleteToken(String token) {
		
		TokenConfirmation tokenConfirmation = tokenRepo.findByToken(token);
		tokenRepo.deleteById(tokenConfirmation.getTokenId());
		
		return "Token is deleted";
	}
	
	public TokenConfirmation setEnable(int id) {
		TokenConfirmation tokenConfirmation = tokenRepo.findById(id).orElse(null);
		
		if(tokenConfirmation == null)
			throw new UsernameNotFoundException("Token is not found");
		
		tokenConfirmation.getUser().setEnabled(true);
		
		return tokenRepo.save(tokenConfirmation);
	}
}

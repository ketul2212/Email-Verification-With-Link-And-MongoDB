package com.ketul.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ketul.module.MyUserDeatils;
import com.ketul.module.TokenConfirmation;
import com.ketul.module.User;
import com.ketul.repo.TokenRepo;
import com.ketul.repo.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private TokenRepo tokenRepo;

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(email);
		
		if(user == null)
			throw new UsernameNotFoundException("User Not Found" + email);
		
		return new MyUserDeatils(user);
	}
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	public void registerToken(User user) {
		TokenConfirmation tokenConfirmation = new TokenConfirmation();
		List<TokenConfirmation> list = tokenRepo.findAll();
		
		if(list.size() == 0)
			tokenConfirmation.setTokenId(1);
		else
			tokenConfirmation.setTokenId(list.get(list.size() - 1).getTokenId() + 1);
		
		String token = UUID.randomUUID().toString();
		token = bCryptPasswordEncoder().encode(token).replaceAll("/", "");
		
		if(token.charAt(token.length() - 1) == '.')
			token += "a";
		
		System.out.println(token);
		tokenConfirmation.setToken(token);
		tokenConfirmation.setCreatedAt(LocalDateTime.now());
		tokenConfirmation.setExpiresAt(LocalDateTime.now().plusMinutes(15));
		tokenConfirmation.setUser(user);
		
		mailService.sendSimpleMail(user.getEmail(), tokenConfirmation.getToken());
		
		tokenRepo.save(tokenConfirmation);
	}
	
	public User registerUser(User user) {
		List<User> list = userRepo.findAll();
		
		if(list.size() == 0)
			user.setId(1);
		else
			user.setId(list.get(list.size() - 1).getId() + 1);
		user.setPass(bCryptPasswordEncoder().encode(user.getPass()));
		
		registerToken(user);
		
		
		
		return userRepo.save(user);
	}

	public String deleteUser(int id) {
		
		userRepo.deleteById(id);
		
		return "User is deleted";
	}

	public User setEnable(int id) {
		User user = userRepo.findById(id).orElse(new User());
		
		if(user == null)
			throw new UsernameNotFoundException("User is not found");
		
		user.setEnabled(true);
		
		return userRepo.save(user);
	}
	
	
	@Scheduled(cron = "*/10 * * * * ?")
	
	public void deleteUserWithoutConformation() {
		List<TokenConfirmation> tokenConfirmationsList = tokenRepo.findAll();
		
		for(TokenConfirmation tokenConfirmation : tokenConfirmationsList) {
			if(LocalDateTime.now().isAfter(tokenConfirmation.getExpiresAt())) {
				User user = userRepo.findById(tokenConfirmation.getUser().getId()).orElse(null);
				if(!user.isEnabled() && user != null) { 
					System.out.println("Bye");
					userRepo.deleteById(user.getId());
					tokenRepo.deleteById(tokenConfirmation.getTokenId());
				}
			}
		}	
		System.out.println("Hello");
	}
}

package com.ketul.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ketul.repo.TokenRepo;

@Service
public class MailService {
	
	@Autowired
	TokenRepo tokenRepo;
	
	@Autowired
	private JavaMailSender mailSender;

	public void sendSimpleMail(String toMail, String token) {
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		
		mailMessage.setFrom("<Enter Your Email>");
		mailMessage.setTo(toMail);
		mailMessage.setText("Conform Your Mail with the below link\n" 
				+ "http://localhost:8080/conform/" + token);
		mailMessage.setSubject("Conformation Mail");
		
		mailSender.send(mailMessage);
	}
}

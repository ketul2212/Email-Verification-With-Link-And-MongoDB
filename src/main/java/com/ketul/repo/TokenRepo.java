package com.ketul.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ketul.module.TokenConfirmation;

public interface TokenRepo extends MongoRepository<TokenConfirmation, Integer> {
	public TokenConfirmation findByToken(String token);
}

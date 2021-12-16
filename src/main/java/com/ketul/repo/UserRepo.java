package com.ketul.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ketul.module.User;

public interface UserRepo extends MongoRepository<User, Integer> {

	public User findByEmail(String email);
}

package com.ketul.module;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import jdk.jfr.Timestamp;

@Document
public class TokenConfirmation {
	
	@Id
    private int tokenId;
	
	@Field
	private String token;
	
	@Timestamp
	private LocalDateTime confirmedAt;
	
	@Timestamp
	private LocalDateTime createdAt;
	
	@Timestamp
	private LocalDateTime expiresAt;
	
	@Field
	private User user;
	
	public TokenConfirmation() {
		super();
	}
	
	

	public TokenConfirmation(String token, LocalDateTime createdAt,
			LocalDateTime expiresAt, User user) {
		super();
		this.token = token;
		this.createdAt = createdAt;
		this.expiresAt = expiresAt;
		this.user = user;
	}

	public int getTokenId() {
		return tokenId;
	}

	public void setTokenId(int tokenId) {
		this.tokenId = tokenId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getConfirmedAt() {
		return confirmedAt;
	}

	public void setConfirmedAt(LocalDateTime confirmedAt) {
		this.confirmedAt = confirmedAt;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(LocalDateTime expiresAt) {
		this.expiresAt = expiresAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "TokenConfirmation [tokenId=" + tokenId + ", token=" + token + ", confirmedAt=" + confirmedAt
				+ ", createdAt=" + createdAt + ", expiresAt=" + expiresAt + ", user=" + user + "]";
	}

	
}

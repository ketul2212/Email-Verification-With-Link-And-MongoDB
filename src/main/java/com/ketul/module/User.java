package com.ketul.module;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class User {
	
	@Id
	private int id;
	
	@Field
	@Indexed(unique=true)
	private String email;
	
	@Field
	private String pass;
	
	@Field
	private boolean isEnabled;

	public User() {
		super();
	}

	public User(String email, String pass, String role, boolean isEnabled) {
		super();
		this.email = email;
		this.pass = pass;
		this.isEnabled = isEnabled;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", pass=" + pass + ", isEnabled=" + isEnabled + "]";
	}
	
	
}

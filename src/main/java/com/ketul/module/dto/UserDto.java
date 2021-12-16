package com.ketul.module.dto;

public class UserDto {
	private String email;
	private String pass;
	
	public UserDto() {
		super();
	}

	public UserDto(String email, String pass) {
		super();
		this.email = email;
		this.pass = pass;
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

	@Override
	public String toString() {
		return "UserDto [email=" + email + ", pass=" + pass + "]";
	}

	
}

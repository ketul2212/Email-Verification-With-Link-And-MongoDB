package com.ketul.module;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDeatils implements UserDetails {
	
	private String email;
	private String pass;
	private boolean enable;
	private List<GrantedAuthority> authorities;

	public MyUserDeatils(User user) {
		this.email = user.getEmail();
		this.pass = user.getPass();
		this.enable = user.isEnabled();
		this.authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return pass;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enable;
	}

}

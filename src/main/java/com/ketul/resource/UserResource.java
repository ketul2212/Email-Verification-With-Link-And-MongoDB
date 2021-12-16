package com.ketul.resource;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ketul.module.User;
import com.ketul.module.dto.UserDto;
import com.ketul.service.MyUserDetailsService;

@RestController
public class UserResource {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@GetMapping("/")
	public String welcomePage() {
		return "<h1>Welcome User</h1>";
	}
	
	@PostMapping("/register")
	public User register(@RequestBody UserDto userDto) {
		return myUserDetailsService.registerUser(mapper.map(userDto, User.class));
	}
	
	@GetMapping("/abc")
	public String abc() {
		return "ABC";
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteUser(@PathVariable int id) {
		return myUserDetailsService.deleteUser(id);
	}
	
	@PutMapping("/update/{id}")
	public User setEnableUser(@PathVariable int id) {
		return myUserDetailsService.setEnable(id);
	}
}

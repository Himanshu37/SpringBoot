package com.himanshu.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.himanshu.journalApp.entity.User;
import com.himanshu.journalApp.service.UserService;

@RestController
@RequestMapping("/public")
public class PublicController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/health-check")
	public String healthCheck() {
		return "Ok";
	}
	
	@PostMapping("/create-user")
	public void createUser(@RequestBody User user) {
		System.out.println("POST /user called with: " + user);
		userService.saveEntry(user);
	}

}

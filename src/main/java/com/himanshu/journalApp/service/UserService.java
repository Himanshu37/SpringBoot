package com.himanshu.journalApp.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.himanshu.journalApp.entity.User;
import com.himanshu.journalApp.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
//	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	public boolean saveNewUser(User user) {
		try {
//			logger.info("Entered to save user");
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setRoles(Arrays.asList("USER"));
			userRepository.save(user);
			return true;
		}catch(Exception e) {
//			logger.error("Error occured for {} ", user.getUserName() , e);
			log.error("Error occured for {} ", user.getUserName() , e);
//			logger.error("hahahah!!!!!");
//			logger.warn("hahahah!!!!!");
//			logger.debug("hahahah!!!!!");
//			logger.trace("hahahah!!!!!");
			return false;
		}
	}
	
	public void saveAdmin(User user) {
		try {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setRoles(Arrays.asList("USER","ADMIN"));
			userRepository.save(user);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void saveUser(User user) {
		try {
			userRepository.save(user);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<User> getAll(){
		return userRepository.findAll();
	}
	
	public Optional<User> findById(ObjectId id) {
		return userRepository.findById(id);
	}
	
	public void deleteById(ObjectId id) {
		userRepository.deleteById(id);
	}
	
	public User findByUserName(String username) {
		return userRepository.findByUserName(username);
	}
	
}

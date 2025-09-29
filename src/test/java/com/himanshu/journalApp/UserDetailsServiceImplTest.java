package com.himanshu.journalApp;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.himanshu.journalApp.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;

import com.himanshu.journalApp.repository.UserRepository;
import com.himanshu.journalApp.service.UserDetailsServiceImpl;
import com.mongodb.assertions.Assertions;

import net.bytebuddy.asm.Advice.Argument;

public class UserDetailsServiceImplTest {

	@InjectMocks
	private UserDetailsServiceImpl userDetailsService;
	
	@Mock
	private UserRepository  userRepository;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void loadUserByUsernameTest() {
		when(userRepository.findByUserName(ArgumentMatchers.anyString())).
		thenReturn(User.builder().userName("ram").password("iii").roles(new ArrayList<>()).build());
		UserDetails user = userDetailsService.loadUserByUsername("ram");
		Assertions.assertNotNull(user);
	}
	
}

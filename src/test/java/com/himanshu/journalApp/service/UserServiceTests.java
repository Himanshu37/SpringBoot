package com.himanshu.journalApp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.himanshu.journalApp.entity.User;
import com.himanshu.journalApp.repository.UserRepository;

@SpringBootTest
public class UserServiceTests {
	
	@Autowired
	private UserRepository userRepository;
	
	@ParameterizedTest
	@ValueSource(strings = {
			"ram",
			"shyam",
			"vipul"
	})
	public void testFindByUserName(String name) {
		assertNotNull(userRepository.findByUserName(name));
	}
	
	@Disabled
	@ParameterizedTest
	@CsvSource({
		"1,1,2",
		"2,10,12",
		"3,3,9"
	})
	public void test(int a, int b, int expected) {
		assertEquals(expected, a+b,"FAILED FOR A : " + a + " , b : " + b + " Expected : " + expected);
	}
	
}

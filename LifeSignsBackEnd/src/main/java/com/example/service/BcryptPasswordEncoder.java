package com.example.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BcryptPasswordEncoder {
	BCryptPasswordEncoder passwordEncoder;
	
	/*
	 * BCrypt password encoder generates a random salt with passwords.
	 * Use the below
	 * 
	BCryptPasswordEncoder a = new BCryptPasswordEncoder();
	for (int i = 0; i < 10; ++i) {
	    String encodedPasswordWithSalt = encoder.encode("123");
	    System.out.println(encodedPassword + "=\"123\" is " + encoder.matches("123", encodedPassword));
	}
	*
	*/
	
	public BcryptPasswordEncoder () {
		passwordEncoder = new BCryptPasswordEncoder();
	}

	public BCryptPasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}
}

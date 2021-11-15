package com.revature.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BcryptPasswordEncoder { // Note the uncapitalized 'c' means it's our class
	BCryptPasswordEncoder passwordEncoder;
	
	/*
	 * BCrypt password encoder generates a random salt with passwords.
	 * Executing the below sample code below 
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	for (int i = 0; i < 5; ++i) {
	    String encodedPasswordWithSalt = encoder.encode("123");
	    System.out.println(encodedPasswordWithSalt + "==\"123\" is " + encoder.matches("123", encodedPasswordWithSalt));
		}
	* will generate
	$2a$10$XwtYJRZ6su.V.amG0Yo4N.lTi6ptvxZkgb6CpUcJUTnxk0RpXv/Jq=="123" is true
	$2a$10$djb7P1Omm0X4bcirV5Ud6.dwBzIAxlif5tbi6YJ.Xwc0wBvaJ9IsO=="123" is true
	$2a$10$UvO/YaoA8b4HkT.Pux2FietwuyZq4ZyNrIy1XTZq8XAX/7Mz3vhW2=="123" is true
	$2a$10$Eo5KDPFqxG5ZCGZxocDjZOm.GKXKQ3IMPn21PgVGW8OyDITgF/Aeu=="123" is true
	$2a$10$uyAWaqMLu/sUDx83MyKlAeAb.OeSXL6pCfj3QLwugUaWb4J1rqcOW=="123" is true
	* If hackers get into the database, they will receive this jumbled garbage that is extremely hard to decrypt.
	* You MUST use the .matches function to check for equality.
	* When using the .matches function, make sure the raw password is the 1st arg and the hashed is 2nd arg
	* Wikipedia for bcrypt here https://en.wikipedia.org/wiki/Bcrypt
	*/
	
	public BcryptPasswordEncoder () {
		passwordEncoder = new BCryptPasswordEncoder();
	}

	public BCryptPasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public String encode(String password) {
		return passwordEncoder.encode(password);
	}
}

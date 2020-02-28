package com.robotiqal.demo.shared.utils;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;
//TO DO move to static methods in interface per Java 8
@Component
public class RandomStringGenerator {
	
	private final Random RANDOM = new SecureRandom();
	private final String ALPHABET = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
	
	public String generateUserId(int length) {
		return generateRandomString(length);	
	}
	
	public String generateRandomString(int length) {
		StringBuilder userId  = new StringBuilder(length);
		for(int i=0; i<length; i++) {
			userId.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));	
		}
		
		return new String(userId);
	}

}

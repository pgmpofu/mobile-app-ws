package com.robotiqal.demo.security;

import com.robotiqal.demo.SpringApplicationContext;

public class SecurityConstants {
	public static final long EXPIRATION_TIME = 864000000L;
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/users";

	public static String getTokenSecret() {

		AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
		return appProperties.getTokenSecret();
	}

}

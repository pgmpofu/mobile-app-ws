package com.robotiqal.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.robotiqal.demo.shared.dto.UserDTO;

public interface UserService extends UserDetailsService {

	UserDTO createUser(UserDTO userDTO);
	UserDTO getUser(String email);
	UserDTO getUserById(String userId);
}

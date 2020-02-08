package com.robotiqal.demo.service.impl;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.robotiqal.demo.io.entity.User;
import com.robotiqal.demo.repository.UserRepository;
import com.robotiqal.demo.service.UserService;
import com.robotiqal.demo.shared.dto.UserDTO;
import com.robotiqal.demo.shared.utils.RandomStringGenerator;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RandomStringGenerator randomStringGenerator;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	private final static int STRING_LENGTH = 30;

	@Override
	public UserDTO createUser(UserDTO userDTO) {
		// TODO Handle duplicate entry violation (SQLException)
		User user = new User();
		BeanUtils.copyProperties(userDTO, user);
		user.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setUserId(randomStringGenerator.generateRandomString(STRING_LENGTH));
		user.setEmailVerificationStatus(Boolean.FALSE);
		user.setEmailVerificationToken(randomStringGenerator.generateRandomString(STRING_LENGTH));
		User storedUser = userRepository.save(user);
		UserDTO storedUserDTO = new UserDTO();
		BeanUtils.copyProperties(storedUser, storedUserDTO);
		return storedUserDTO;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findUserByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Cound not find user by email");
		}

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getEncryptedPassword(),
				new ArrayList<>());
	}

}

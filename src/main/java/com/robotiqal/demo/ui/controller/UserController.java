package com.robotiqal.demo.ui.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robotiqal.demo.service.UserService;
import com.robotiqal.demo.shared.dto.UserDTO;
import com.robotiqal.demo.ui.model.request.UserDetailsRequestModel;
import com.robotiqal.demo.ui.model.response.UserRest;

@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping(path="/{userId}",
			produces= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public UserRest getUser(@PathVariable String userId) {
		UserRest userRest = new UserRest();
		UserDTO  userDTO =  userService.getUserById(userId);
		BeanUtils.copyProperties(userDTO, userRest);
		return userRest;
	}

	@PostMapping
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {
		UserRest userRest = new UserRest();
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(userDetails, userDTO);

		UserDTO createdUser = userService.createUser(userDTO);
		BeanUtils.copyProperties(createdUser, userRest);

		return userRest;
	}

	@PutMapping
	public String updateUser() {
		return "updateUser() was called";
	}

	@DeleteMapping
	public String deleteUser() {
		return "deletedUser() was called";
	}

}

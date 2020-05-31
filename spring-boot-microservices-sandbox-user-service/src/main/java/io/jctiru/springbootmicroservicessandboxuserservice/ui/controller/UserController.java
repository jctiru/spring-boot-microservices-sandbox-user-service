package io.jctiru.springbootmicroservicessandboxuserservice.ui.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jctiru.springbootmicroservicessandboxuserservice.service.UserService;
import io.jctiru.springbootmicroservicessandboxuserservice.shared.dto.UserDto;
import io.jctiru.springbootmicroservicessandboxuserservice.ui.model.request.CreateUserRequestModel;
import io.jctiru.springbootmicroservicessandboxuserservice.ui.model.response.CreateUserResponseModel;
import io.jctiru.springbootmicroservicessandboxuserservice.ui.model.response.UserResponseModel;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private Environment env;

	@GetMapping("/status/check")
	public String status() {
		return "Working on port " + env.getProperty("local.server.port");
	}

	@PostMapping
	public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel userDetails) {
		UserDto userDto = modelMapper.map(userDetails, UserDto.class);

		UserDto createdUser = userService.createUser(userDto);

		CreateUserResponseModel returnValue = modelMapper.map(createdUser, CreateUserResponseModel.class);

		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserResponseModel> getUser(@PathVariable("userId") String userId) {
		
		UserDto userDto = userService.getUserByUserId(userId);
		UserResponseModel returnValue = modelMapper.map(userDto, UserResponseModel.class);
		
		return ResponseEntity.status(HttpStatus.OK).body(returnValue);
	}

}

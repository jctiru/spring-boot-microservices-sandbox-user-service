package io.jctiru.springbootmicroservicessandboxuserservice.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import io.jctiru.springbootmicroservicessandboxuserservice.shared.dto.UserDto;

public interface UserService extends UserDetailsService {

	UserDto createUser(UserDto userDetails);

	UserDto getUserDetailsByEmail(String email);

}

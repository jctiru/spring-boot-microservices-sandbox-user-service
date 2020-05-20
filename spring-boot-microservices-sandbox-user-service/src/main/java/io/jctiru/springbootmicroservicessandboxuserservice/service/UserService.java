package io.jctiru.springbootmicroservicessandboxuserservice.service;

import io.jctiru.springbootmicroservicessandboxuserservice.shared.dto.UserDto;

public interface UserService {

	UserDto createUser(UserDto userDetails);

}

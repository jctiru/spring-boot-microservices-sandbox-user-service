package io.jctiru.springbootmicroservicessandboxuserservice.service.impl;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import io.jctiru.springbootmicroservicessandboxuserservice.io.entity.UserEntity;
import io.jctiru.springbootmicroservicessandboxuserservice.io.repository.UserRepository;
import io.jctiru.springbootmicroservicessandboxuserservice.service.UserService;
import io.jctiru.springbootmicroservicessandboxuserservice.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto userDetails) {
		userDetails.setUserId(UUID.randomUUID().toString());
		userDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));

		UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);

		userRepository.save(userEntity);

		return modelMapper.map(userEntity, UserDto.class);
	}

}

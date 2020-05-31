package io.jctiru.springbootmicroservicessandboxuserservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import io.jctiru.springbootmicroservicessandboxuserservice.io.entity.UserEntity;
import io.jctiru.springbootmicroservicessandboxuserservice.io.external.AlbumServiceClient;
import io.jctiru.springbootmicroservicessandboxuserservice.io.repository.UserRepository;
import io.jctiru.springbootmicroservicessandboxuserservice.service.UserService;
import io.jctiru.springbootmicroservicessandboxuserservice.shared.dto.UserDto;
import io.jctiru.springbootmicroservicessandboxuserservice.ui.model.response.AlbumResponseModel;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

//	@Autowired
//	private RestTemplate restTemplate;

	@Autowired
	private AlbumServiceClient albumServiceClient;

	@Override
	public UserDto createUser(UserDto userDetails) {
		userDetails.setUserId(UUID.randomUUID().toString());
		userDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));

		UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);

		userRepository.save(userEntity);

		return modelMapper.map(userEntity, UserDto.class);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findUserByEmail(email);

		if (userEntity == null) {
			throw new UsernameNotFoundException(email);
		}

		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), true, true, true, true,
				new ArrayList<>());
	}

	@Override
	public UserDto getUserDetailsByEmail(String email) {
		UserEntity userEntity = userRepository.findUserByEmail(email);

		if (userEntity == null) {
			throw new UsernameNotFoundException(email);
		}

		return modelMapper.map(userEntity, UserDto.class);
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		UserEntity userEntity = userRepository.findUserByUserId(userId);

		if (userEntity == null) {
			throw new UsernameNotFoundException(userId);
		}

		UserDto userDto = modelMapper.map(userEntity, UserDto.class);
//		String albumUrl = String.format("http://album-service/users/%s/albums", userId);
//		ResponseEntity<List<AlbumResponseModel>> albumListResponse = restTemplate.exchange(albumUrl, HttpMethod.GET,
//				null, new ParameterizedTypeReference<List<AlbumResponseModel>>() {
//				});
//		List<AlbumResponseModel> albumList = albumListResponse.getBody();
		List<AlbumResponseModel> albumList = albumServiceClient.getAlbums(userId);
		userDto.setAlbumList(albumList);

		return userDto;
	}

}

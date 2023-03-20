package com.gogreen.apis.user.controller;

import com.gogreen.apis.user.boundary.util.mapper.GoGreenUserMapper;
import com.gogreen.apis.user.repository.GoGreenUserRepository;
import com.gogreen.core.exception.UserNotFoundException;
import com.gogreen.models.auth.dto.GoGreenUserDto;
import com.gogreen.models.auth.entity.GoGreenUserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
	private final GoGreenUserRepository userRepository;
	private final GoGreenUserMapper userMapper;

	public GoGreenUserDto retrieveUser(final Long id) {
		GoGreenUserEntity goGreenUserEntity = this.userRepository.findByIdAndDeletedFalse(
				id).orElseThrow(() -> {
			throw new UserNotFoundException();
		});
		return this.userMapper.toDto(goGreenUserEntity);
	}
}

package com.gogreen.apis.user.controller;

import com.gogreen.apis.user.boundary.util.mapper.GoGreenUserMapper;
import com.gogreen.apis.user.repository.GoGreenUserRepository;
import com.gogreen.core.exception.UserAlreadyExistsException;
import com.gogreen.models.auth.dto.AuthenticationResponseDto;
import com.gogreen.models.auth.dto.GoGreenUserDto;
import com.gogreen.models.auth.entity.GoGreenUserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	private final GoGreenUserRepository goGreenUserRepository;
	private final GoGreenUserMapper goGreenUserMapper;

	private final PasswordEncoder passwordEncoder;

	public AuthenticationResponseDto createUser(GoGreenUserDto goGreenUserDto) {
		if (this.goGreenUserRepository.findByEmailAndDeletedIsFalse(
				goGreenUserDto.getEmail()).isPresent()) {
			throw new UserAlreadyExistsException();
		}
		GoGreenUserEntity goGreenUserEntity = goGreenUserMapper.toEntity(goGreenUserDto);
		goGreenUserEntity.setPassword(
				passwordEncoder.encode(goGreenUserEntity.getPassword()));
		this.goGreenUserRepository.saveAndFlush(goGreenUserEntity);

		return AuthenticationResponseDto.builder().email(goGreenUserDto.getEmail())
				.build();
	}
}

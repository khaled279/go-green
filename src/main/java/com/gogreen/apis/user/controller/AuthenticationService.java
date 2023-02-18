package com.gogreen.apis.user.controller;

import com.gogreen.apis.user.repository.GoGreenUserRepository;
import com.gogreen.core.exception.UserAlreadyExistsException;
import com.gogreen.models.auth.dto.AuthenticationResponseDto;
import com.gogreen.models.auth.dto.GoGreenUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	private final GoGreenUserRepository goGreenUserRepository;

	public AuthenticationResponseDto createUser(GoGreenUserDto goGreenUserDto) {
		if (this.goGreenUserRepository.findByEmailAndDeletedFalse(
				goGreenUserDto.getEmail()).isPresent()) {
			throw new UserAlreadyExistsException();
		}

		return null;
	}
}

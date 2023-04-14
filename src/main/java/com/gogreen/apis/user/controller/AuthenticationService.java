package com.gogreen.apis.user.controller;

import com.gogreen.apis.cart.repository.CartRepository;
import com.gogreen.apis.user.boundary.util.mapper.GoGreenUserMapper;
import com.gogreen.apis.user.repository.CommunityUserRepository;
import com.gogreen.apis.user.repository.GoGreenUserRepository;
import com.gogreen.core.exception.UserAlreadyExistsException;
import com.gogreen.models.auth.dtos.AuthenticationResponseDto;
import com.gogreen.models.auth.dtos.GoGreenUserDto;
import com.gogreen.models.auth.entities.GoGreenUserEntity;
import com.gogreen.models.auth.enums.UserTypeEnum;
import com.gogreen.models.cart.entities.CartEntity;
import com.gogreen.models.user.entities.CommunityUserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	private final GoGreenUserRepository goGreenUserRepository;
	private final GoGreenUserMapper goGreenUserMapper;
	private final CommunityUserRepository communityUserRepository;
	private final CartRepository cartRepository;

	private final PasswordEncoder passwordEncoder;

	private final AuthenticationManager authenticationManager;

	public AuthenticationResponseDto createUser(GoGreenUserDto goGreenUserDto) {
		if (this.goGreenUserRepository.findByEmailAndDeletedIsFalse(
				goGreenUserDto.getEmail()).isPresent()) {
			throw new UserAlreadyExistsException();
		}
		GoGreenUserEntity goGreenUserEntity = goGreenUserMapper.toEntity(goGreenUserDto);
		goGreenUserEntity.setPassword(
				passwordEncoder.encode(goGreenUserDto.getPassword()));
		goGreenUserEntity.setUserTypeEnum(UserTypeEnum.COMMUNITY);
		CommunityUserEntity communityUserEntity = CommunityUserEntity.builder()
				.points(BigDecimal.ZERO).build();
		communityUserEntity = this.communityUserRepository.saveAndFlush(
				communityUserEntity);
		CartEntity cartEntity = CartEntity.builder().user(communityUserEntity)
				.total(BigDecimal.ZERO).build();
		this.cartRepository.saveAndFlush(cartEntity);
		goGreenUserEntity.setUserDetailsId(communityUserEntity.getId());
		this.goGreenUserRepository.saveAndFlush(goGreenUserEntity);

		return AuthenticationResponseDto.builder().email(goGreenUserDto.getEmail())
				.build();
	}

}

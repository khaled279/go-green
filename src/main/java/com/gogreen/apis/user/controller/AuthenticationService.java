package com.gogreen.apis.user.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gogreen.apis.user.boundary.util.mapper.GoGreenUserMapper;
import com.gogreen.apis.user.repository.GoGreenUserRepository;
import com.gogreen.core.constants.SecurityConstants;
import com.gogreen.core.exception.UserAlreadyExistsException;
import com.gogreen.core.exception.UserNotFoundException;
import com.gogreen.models.auth.dto.AuthenticationRequestDto;
import com.gogreen.models.auth.dto.AuthenticationResponseDto;
import com.gogreen.models.auth.dto.GoGreenUserDto;
import com.gogreen.models.auth.entity.GoGreenUserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	private final GoGreenUserRepository goGreenUserRepository;
	private final GoGreenUserMapper goGreenUserMapper;

	private final PasswordEncoder passwordEncoder;

	private final AuthenticationManager authenticationManager;

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

	public AuthenticationResponseDto authenticateUser(
			AuthenticationRequestDto authenticationRequest) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							authenticationRequest.getEmail(),
							authenticationRequest.getPassword()));
			ArrayList<String> authorities = new ArrayList<>();
			for (GrantedAuthority ga : authentication.getAuthorities()) {
				authorities.add(ga.getAuthority());
			}

			String token = JWT.create()
					.withSubject(authentication.getPrincipal().toString())
					.withClaim("Authorities", authorities)
					.withClaim("email", authenticationRequest.getEmail()).withExpiresAt(
							new Date(
									System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
					.sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));
			return AuthenticationResponseDto.builder().token(token)
					.email(((GoGreenUserEntity) authentication.getPrincipal()).getEmail())
					.build();

		}
		catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}

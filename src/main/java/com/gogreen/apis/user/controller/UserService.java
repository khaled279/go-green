package com.gogreen.apis.user.controller;

import com.gogreen.apis.user.boundary.util.mapper.CommunityUserMapper;
import com.gogreen.apis.user.boundary.util.mapper.GoGreenUserMapper;
import com.gogreen.apis.user.repository.CommunityUserRepository;
import com.gogreen.apis.user.repository.GoGreenCommunityUserWrapper;
import com.gogreen.apis.user.repository.GoGreenUserRepository;
import com.gogreen.core.exception.SystemException;
import com.gogreen.core.exception.UserNotFoundException;
import com.gogreen.models.auth.dtos.UserDetailsImpl;
import com.gogreen.models.user.dtos.CommunityUserDto;
import com.gogreen.models.user.entities.CommunityUserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class UserService {
	private final GoGreenUserRepository userRepository;
	private final GoGreenUserMapper userMapper;

	private final CommunityUserRepository communityUserRepository;
	private final CommunityUserMapper communityUserMapper;

	public CommunityUserDto retrieveUser(final Long id) {
		GoGreenCommunityUserWrapper goGreenCommunityUser = this.userRepository.findByIdAndDeletedFalse(
				id);
		if (goGreenCommunityUser.getGoGreenUserEntity() == null) {
			throw new UserNotFoundException();
		}
		CommunityUserDto communityUserDto = communityUserMapper.toDto(
				goGreenCommunityUser.getCommunityUserEntity());
		communityUserDto.setGoGreenUserDto(
				this.userMapper.toDto(goGreenCommunityUser.getGoGreenUserEntity()));
		return communityUserDto;

	}

	public void addPoints(BigDecimal points, UserDetailsImpl userDetails) {

		CommunityUserEntity communityUserEntity = this.communityUserRepository.findByIdAndDeletedFalse(
				userDetails.getUserDetailsId()).orElseThrow(
				() -> new SystemException(HttpStatus.NOT_FOUND, "invalid user"));
		communityUserEntity.setPoints(communityUserEntity.getPoints().add(points));
		this.communityUserRepository.saveAndFlush(communityUserEntity);
	}
}

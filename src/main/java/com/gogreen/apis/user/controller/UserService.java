package com.gogreen.apis.user.controller;

import com.gogreen.apis.user.boundary.util.mapper.CommunityUserMapper;
import com.gogreen.apis.user.boundary.util.mapper.GoGreenUserMapper;
import com.gogreen.apis.user.repository.GoGreenCommunityUserWrapper;
import com.gogreen.apis.user.repository.GoGreenUserRepository;
import com.gogreen.core.exception.UserNotFoundException;
import com.gogreen.models.user.dtos.CommunityUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
	private final GoGreenUserRepository userRepository;
	private final GoGreenUserMapper userMapper;
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
}

package com.gogreen.apis.admin.user.control;

import com.gogreen.apis.user.boundary.util.mapper.CommunityUserMapper;
import com.gogreen.apis.user.boundary.util.mapper.GoGreenUserMapper;
import com.gogreen.apis.user.repository.GoGreenCommunityUserWrapper;
import com.gogreen.apis.user.repository.GoGreenUserRepository;
import com.gogreen.models.user.dtos.CommunityUserDto;
import com.gogreen.models.user.dtos.UserSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAdministrationService {
	private final GoGreenUserRepository goGreenUserRepository;
	private final GoGreenUserMapper goGreenUserMapper;
	private final CommunityUserMapper communityUserMapper;

	public Page<CommunityUserDto> listUsers(UserSearchDto userSearchDto,
			Pageable pageable) {
		if (userSearchDto.getEmail() != null) {
			userSearchDto.setEmail("%" + userSearchDto.getEmail() + "%");
		}
		if (userSearchDto.getName() != null) {
			userSearchDto.setName("%" + userSearchDto.getName() + "%");
		}

		Page<GoGreenCommunityUserWrapper> goGreenCommunityUserWrapperPage = this.goGreenUserRepository.listAll(
				userSearchDto.getId(), userSearchDto.getPoints(),
				userSearchDto.getEmail(), userSearchDto.getName(), pageable);
		List<CommunityUserDto> communityUserDtos = new ArrayList<>();
		for (GoGreenCommunityUserWrapper userWrapper : goGreenCommunityUserWrapperPage.getContent()) {
			CommunityUserDto communityUserDto = this.communityUserMapper.toDto(
					userWrapper.getCommunityUserEntity());
			communityUserDto.setGoGreenUserDto(
					this.goGreenUserMapper.toDto(userWrapper.getGoGreenUserEntity()));
			communityUserDtos.add(communityUserDto);
		}
		return new PageImpl<>(communityUserDtos,
				goGreenCommunityUserWrapperPage.getPageable(),
				goGreenCommunityUserWrapperPage.getTotalElements());
	}

}

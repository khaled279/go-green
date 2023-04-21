package com.gogreen.apis.admin.user.boundary;

import com.gogreen.apis.admin.user.control.UserAdministrationService;
import com.gogreen.models.user.dtos.CommunityUserDto;
import com.gogreen.models.user.dtos.UserSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/user")
@RequiredArgsConstructor
public class UserAdministrationApi {
	private final UserAdministrationService userAdministrationService;

	@GetMapping("")
	@PreAuthorize("hasAuthority('user_list')")
	Page<CommunityUserDto> listCommunityUsers(UserSearchDto userSearchDto,
			@RequestParam(required = false) Long pageSize,
			@RequestParam(required = false, defaultValue = "0") Long pageNo,
			@RequestParam(required = false, defaultValue = "ASC") Sort.Direction direction) {
		return this.userAdministrationService.listUsers(userSearchDto, pageSize == null ?
				Pageable.unpaged() :
				PageRequest.of(pageNo.intValue(), pageSize.intValue()));
	}
}

package com.gogreen.apis.user.boundary;

import com.gogreen.apis.user.controller.UserService;
import com.gogreen.models.auth.dtos.GoGreenUserDto;
import com.gogreen.models.auth.dtos.UserDetailsImpl;
import com.gogreen.models.user.dtos.CommunityUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("community/user")
public class CommunityUserApi {
	private final UserService userService;

	@GetMapping("")
	public ResponseEntity<CommunityUserDto> retrieveUser(Authentication authentication) {
		return ResponseEntity.ok(this.userService.retrieveUser(
				((UserDetailsImpl) authentication.getPrincipal()).getId()));
	}
}

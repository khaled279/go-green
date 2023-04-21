package com.gogreen.apis.user.boundary;

import com.gogreen.apis.user.controller.UserService;
import com.gogreen.models.auth.dtos.GoGreenUserDto;
import com.gogreen.models.auth.dtos.UserDetailsImpl;
import com.gogreen.models.user.dtos.CommunityUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

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

	@PutMapping("/add-points")
	public ResponseEntity<Void> addPoints(@RequestParam BigDecimal points,
			@AuthenticationPrincipal UserDetailsImpl userDetails) {
		this.userService.addPoints(points, userDetails);
		return ResponseEntity.ok().build();
	}
}

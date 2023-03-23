package com.gogreen.apis.user.boundary;

import com.gogreen.apis.user.controller.UserService;
import com.gogreen.models.auth.dtos.GoGreenUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserApi {
	private final UserService userService;

	@GetMapping("/{id}")
	public ResponseEntity<GoGreenUserDto> retrieveUser(@PathVariable Long id) {
		return ResponseEntity.ok(this.userService.retrieveUser(id));
	}
}

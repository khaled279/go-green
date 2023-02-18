package com.gogreen.apis.user.boundary;

import com.gogreen.apis.user.controller.AuthenticationService;
import com.gogreen.apis.user.repository.GoGreenUserRepository;
import com.gogreen.models.auth.dto.AuthenticationResponseDto;
import com.gogreen.models.auth.dto.GoGreenUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "auth")
@RequiredArgsConstructor
public class AuthenticationApi {
	private final AuthenticationService authenticationService;
	private final GoGreenUserRepository goGreenUserRepository;

	@PostMapping("/user")
	ResponseEntity<AuthenticationResponseDto> createUser(@RequestBody GoGreenUserDto goGreenUserDto){

		return ResponseEntity.ok(this.authenticationService.createUser(goGreenUserDto));
	}

}

package com.gogreen.apis.user.boundary;

import com.gogreen.apis.user.controller.AuthenticationService;
import com.gogreen.models.auth.dtos.AuthenticationRequestDto;
import com.gogreen.models.auth.dtos.AuthenticationResponseDto;
import com.gogreen.models.auth.dtos.GoGreenUserDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "auth")
@AllArgsConstructor
public class AuthenticationApi {
	private final AuthenticationService authenticationService;

	@PostMapping("/user")
	ResponseEntity<AuthenticationResponseDto> createUser(
			@Valid @RequestBody GoGreenUserDto goGreenUserDto) {

		return ResponseEntity.ok(this.authenticationService.createUser(goGreenUserDto));
	}

	@PostMapping("/login")
	ResponseEntity<AuthenticationResponseDto> authenticateUser(
			@Valid @RequestBody AuthenticationRequestDto authenticationRequest) {
		return ResponseEntity.noContent().build();
	}

}

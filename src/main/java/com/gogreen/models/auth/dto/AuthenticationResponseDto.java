package com.gogreen.models.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class AuthenticationResponseDto {
	private String token;
	private String email;
}

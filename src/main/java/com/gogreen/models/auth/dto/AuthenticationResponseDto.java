package com.gogreen.models.auth.dto;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponseDto {
	private String token;
	private String email;
}

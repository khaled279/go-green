package com.gogreen.models.auth.dtos;

import com.gogreen.models.auth.enums.UserTypeEnum;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponseDto {
	private String token;
	private String email;

	private UserTypeEnum userTypeEnum;

	private Long userId;
}

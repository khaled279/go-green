package com.gogreen.models.auth.dto;

import com.gogreen.models.base.dto.BaseDto;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class GoGreenUserDto extends BaseDto {
	private String email;
	private String password;

	private String name;

	private String phoneNumber;

}




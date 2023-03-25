package com.gogreen.models.auth.dtos;

import com.gogreen.models.base.dto.BaseDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class GoGreenUserDto extends BaseDto {
	@NotBlank(message = "Email is required")
	private String email;
	@NotBlank(message = "name is required")
	private String name;
	private String phoneNumber;

	private String password;

}




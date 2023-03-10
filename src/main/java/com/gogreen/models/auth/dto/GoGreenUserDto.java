package com.gogreen.models.auth.dto;

import com.gogreen.models.base.dto.BaseDto;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class GoGreenUserDto extends BaseDto {
	@NotBlank(message = "Email is required")
	private String email;
	@NotBlank(message = "Password Is required")
	private String password;
	@NotBlank(message = "name is required")
	private String name;
	private String phoneNumber;

}




package com.gogreen.models.auth.dto;

import com.gogreen.models.auth.entity.Role;
import com.gogreen.models.base.dto.BaseDto;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.util.Set;

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

}




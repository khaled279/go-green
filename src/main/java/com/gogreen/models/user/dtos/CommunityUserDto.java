package com.gogreen.models.user.dtos;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.gogreen.models.auth.dtos.GoGreenUserDto;
import com.gogreen.models.base.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommunityUserDto extends BaseDto {
	private Long points;

	private GoGreenUserDto goGreenUserDto;
}

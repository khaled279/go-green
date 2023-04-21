package com.gogreen.models.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchDto {
	private String name;
	private String email;
	private Long id;

	private BigDecimal points;
}

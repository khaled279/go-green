package com.gogreen.models.base.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SuperBaseDto {
	protected Long id;
}

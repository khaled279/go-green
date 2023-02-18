package com.gogreen.models.base.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BaseDto extends SuperBaseDto {
	protected Boolean enabled;
	protected Boolean deleted;

	protected String createdAt;

	protected String lastUpdatedAt;

}

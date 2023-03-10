package com.gogreen.models.base.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@Setter
@Getter
@SuperBuilder
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public class BaseDto extends SuperBaseDto {
	protected boolean enabled;
	protected boolean deleted;

	protected Timestamp createdAt;

	protected Timestamp lastUpdatedAt;

}

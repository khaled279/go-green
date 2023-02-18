package com.gogreen.models.base.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@MappedSuperclass
public class SuperBaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
}

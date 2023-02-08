package com.gogreen.models.common;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class SuperBaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column(name = "enabled")
	private Boolean enabled;
	@Column(name = "deleted")
	private Boolean deleted;

}

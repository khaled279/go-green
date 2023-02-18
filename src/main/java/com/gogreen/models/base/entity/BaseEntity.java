package com.gogreen.models.base.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@MappedSuperclass
public class BaseEntity extends SuperBaseEntity {
	@Column(name = "enabled")
	protected Boolean enabled;
	@Column(name = "deleted")
	protected Boolean deleted;

	@Column(name = "created_at")
	protected Timestamp createdAt;

	@Column(name = "last_updated_at")
	protected Timestamp lastUpdatedAt;

	@PrePersist
	protected void onCreate() {
		createdAt = Timestamp.valueOf(LocalDateTime.now());
	}

	@PreUpdate
	protected void onUpdate() {
		lastUpdatedAt = Timestamp.valueOf(LocalDateTime.now());
	}
}

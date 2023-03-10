package com.gogreen.models.base.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity extends SuperBaseEntity {
	@Column(name = "enabled", nullable = false)
	@Builder.Default
	protected boolean enabled = true;
	@Column(name = "deleted", nullable = false)
	protected boolean deleted;

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

package com.gogreen.models.auth.entity;

import com.gogreen.models.base.entity.SuperBaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@SuperBuilder
@Getter
@Setter
public class Role extends SuperBaseEntity {

	private String role;
	@ManyToMany
	@JoinTable(name = "role_authority", joinColumns = {
			@JoinColumn(name = "fk_role") }, inverseJoinColumns = {
			@JoinColumn(name = "fk_authority") })
	private Set<Authority> authorities = new HashSet<>();
	@ManyToMany(mappedBy = "roles")
	private Set<GoGreenUserEntity> users = new HashSet<>();
}

package com.gogreen.models.auth.entity;

import com.gogreen.models.base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@SuperBuilder
public class GoGreenUserEntity extends BaseEntity {
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;

	@Column(name = "name")
	private String name;

	@Column(name = "phone_number")
	private String phoneNumber;
	@ManyToMany
	@JoinTable(name = "user_role", joinColumns = {
			@JoinColumn(name = "fk_user") }, inverseJoinColumns = {
			@JoinColumn(name = "fk_role") })
	private Set<Role> roles = new HashSet();
}

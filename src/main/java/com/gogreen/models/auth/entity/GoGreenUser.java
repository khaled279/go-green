package com.gogreen.models.auth.entity;

import com.gogreen.models.common.SuperBaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@Builder
public class GoGreenUser extends SuperBaseEntity {
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;

	@Column(name = "name")
	private String name;

	@Column(name = "phone_number")
	private String phoneNumber;
	@ManyToMany
	@JoinTable(name = "user_roles", joinColumns = {
			@JoinColumn(name = "fk_user") }, inverseJoinColumns = {
			@JoinColumn(name = "fk_role") })
	private Set<Role> roles = new HashSet();
}

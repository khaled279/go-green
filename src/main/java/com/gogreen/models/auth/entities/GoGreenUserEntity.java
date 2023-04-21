package com.gogreen.models.auth.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gogreen.models.auth.enums.UserTypeEnum;
import com.gogreen.models.base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@SuperBuilder
@Table(name = "go_green_user")
@AllArgsConstructor
@NoArgsConstructor
public class GoGreenUserEntity extends BaseEntity {
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;

	@Column(name = "name")
	private String name;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "user_type")
	@Enumerated(value = EnumType.STRING)
	private UserTypeEnum userTypeEnum;

	@Column(name = "user_details_id")
	private Long userDetailsId;

	@ManyToMany
	@JoinTable(name = "user_role", joinColumns = {
			@JoinColumn(name = "fk_user") }, inverseJoinColumns = {
			@JoinColumn(name = "fk_role") })
	private Set<Role> roles = new HashSet();

}

package com.gogreen.models.auth.entity;

import com.gogreen.models.base.entity.SuperBaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Authority extends SuperBaseEntity {
	private String authorityName;
	@ManyToMany(mappedBy = "authorities")
	private Set<Role> roles = new HashSet<>();

}

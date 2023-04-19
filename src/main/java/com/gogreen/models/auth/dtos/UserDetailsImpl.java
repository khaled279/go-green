package com.gogreen.models.auth.dtos;

import com.gogreen.models.auth.entities.Authority;
import com.gogreen.models.auth.entities.Role;
import com.gogreen.models.auth.enums.UserTypeEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Setter
@Getter
public class UserDetailsImpl implements UserDetails {
	private final String email;

	private final Long id;

	private final String password;

	private final UserTypeEnum userType;

	private final Long userDetailsId;
	private final boolean accountNonExpired;

	private final boolean accountNonLocked;

	private final boolean credentialsNonExpired;

	private final boolean enabled;

	public UserDetailsImpl(Long id, String email, String password, UserTypeEnum userType,
			Long userDetailsId, boolean accountNonExpired, boolean accountNonLocked,
			boolean credentialsNonExpired, boolean enabled, Set<Role> roles) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.userType = userType;
		this.userDetailsId = userDetailsId;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.enabled = enabled;
		this.authorities = reOrderAuthorities(roles);
	}

	private Set<GrantedAuthority> authorities;

	public UserDetailsImpl(Long id, String email, String password, UserTypeEnum userType,
			Long userDetailsId, Set<Role> roles) {
		this(id, email, password, userType, userDetailsId, true, true, true, true, roles);
	}

	private Set<GrantedAuthority> reOrderAuthorities(Collection<Role> roles) {
		if (roles == null) {
			return new TreeSet<>();
		}
		Set<Authority> authorities = new HashSet<>();
		Set<GrantedAuthority> sortedAuthorities = new HashSet<GrantedAuthority>();
		for (Role goGreenRole : roles) {
			authorities.addAll(goGreenRole.getAuthorities());
		}
		for (Authority goGreenAuthority : authorities) {
			Assert.notNull(goGreenAuthority,
					"GrantedAuthority list cannot contain any null elements");
			sortedAuthorities.add(
					new SimpleGrantedAuthority(goGreenAuthority.getAuthorityName()));
		}

		return sortedAuthorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

}

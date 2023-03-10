package com.gogreen.models.auth.dto;

import com.gogreen.models.auth.entity.Authority;
import com.gogreen.models.auth.entity.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.awt.*;
import java.util.Collection;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

@Data
public class UserDetailsImpl implements UserDetails {
	private final String email;

	private final String password;

	private final boolean accountNonExpired;

	private final boolean accountNonLocked;

	private final boolean credentialsNonExpired;

	private final boolean enabled;

	public UserDetailsImpl(String email, String password, boolean accountNonExpired,
			boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled,
			Set<Role> roles) {
		this.email = email;
		this.password = password;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.enabled = enabled;
		this.authorities = reOrderAuthorities(roles);
	}

	private final Set<GrantedAuthority> authorities;

	public UserDetailsImpl(String email, String password, Set<Role> roles) {
		this(email, password, true, true, true, true, roles);
	}

	private Set<GrantedAuthority> reOrderAuthorities(Collection<Role> roles) {

		Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
		Set<Authority> authorities = new TreeSet<>();
		SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<>();
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

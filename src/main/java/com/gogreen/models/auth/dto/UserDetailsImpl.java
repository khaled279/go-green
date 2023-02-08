package com.gogreen.models.auth.dto;

import lombok.Data;
import org.apache.catalina.LifecycleState;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

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
			Collection<? extends GrantedAuthority> authorities) {
		this.email = email;
		this.password = password;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.enabled = enabled;
		this.authorities = reOrderAuthorities(authorities);
	}

	private final Set<GrantedAuthority> authorities;

	public UserDetailsImpl(String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this(email, password, true, true, true, true, authorities);
	}

	private Set<GrantedAuthority> reOrderAuthorities(
			Collection<? extends GrantedAuthority> authorities) {

		Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");

		SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<>();
		for (GrantedAuthority grantedAuthority : authorities) {
			Assert.notNull(grantedAuthority,
					"GrantedAuthority list cannot contain any null elements");
			sortedAuthorities.add(grantedAuthority);
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

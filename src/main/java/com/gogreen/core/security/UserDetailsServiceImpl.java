package com.gogreen.core.security;

import com.gogreen.models.auth.dto.UserDetailsImpl;
import com.gogreen.models.auth.entity.GoGreenUserEntity;
import com.gogreen.apis.user.repository.GoGreenUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
	private final GoGreenUserRepository goGreenUserRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		GoGreenUserEntity user = this.goGreenUserRepository.findByEmailAndDeletedFalse(email)
				.orElseThrow(() -> new UsernameNotFoundException("Wrong Email or Password"));

		return new UserDetailsImpl(user.getEmail() , user.getPassword(), user.getRoles());
	}
}

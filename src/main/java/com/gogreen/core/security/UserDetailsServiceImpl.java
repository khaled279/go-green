package com.gogreen.core.security;

import com.gogreen.apis.user.boundary.util.mapper.GoGreenUserMapper;
import com.gogreen.apis.user.repository.GoGreenUserRepository;
import com.gogreen.models.auth.dto.GoGreenUserDto;
import com.gogreen.models.auth.dto.UserDetailsImpl;
import com.gogreen.models.auth.entity.GoGreenUserEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service("userDetailsService")
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
	private final GoGreenUserRepository goGreenUserRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		GoGreenUserEntity user = this.goGreenUserRepository.findByEmailAndDeletedIsFalse(
				email).orElseThrow(
				() -> new UsernameNotFoundException("Wrong Email or Password"));

		return new UserDetailsImpl(user.getEmail(), user.getPassword(), user.getRoles());
	}
}

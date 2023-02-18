package com.gogreen.core.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gogreen.core.exception.UserNotFoundException;
import com.gogreen.models.auth.dto.UserDetailsImpl;
import com.gogreen.models.auth.entity.GoGreenUserEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@AllArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private final PasswordEncoder passwordEncoder;
	private final UserDetailsService userDetailsService;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		super.attemptAuthentication(request, response);
		GoGreenUserEntity user = null;
		UserDetailsImpl userDetails;
		try {
			user = new ObjectMapper().readValue(request.getInputStream(),
					GoGreenUserEntity.class);
			userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(
					user.getEmail());
			passwordEncoder.matches(user.getPassword(), userDetails.getPassword());

		}
		catch (IOException e) {
			throw new UserNotFoundException();
		}

		return new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
				userDetails.getPassword(), userDetails.getAuthorities());
	}
}

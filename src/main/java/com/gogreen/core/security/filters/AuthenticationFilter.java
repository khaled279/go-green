package com.gogreen.core.security.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gogreen.models.auth.dtos.UserDetailsImpl;
import com.gogreen.models.auth.entities.GoGreenUserEntity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static com.gogreen.core.security.Constants.EXPIRATION_TIME;
import static com.gogreen.core.security.Constants.SECRET;

@Service
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private final PasswordEncoder passwordEncoder;
	private final UserDetailsService userDetailsService;

	private final AuthenticationManager authenticationManager;

	private final ObjectMapper objectMapper;

	@Override
	@Autowired
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		super.setAuthenticationManager(authenticationManager);
	}

	public AuthenticationFilter(AuthenticationManager authenticationManager,
			PasswordEncoder passwordEncoder, UserDetailsService userDetailsService,
			ObjectMapper objectMapper) {
		super(authenticationManager);
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
		setFilterProcessesUrl("/auth/login");
		this.objectMapper = objectMapper;
	}

	@Override
	@SneakyThrows
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws RuntimeException {
		GoGreenUserEntity user = null;
		System.out.println("***************** authenticating");
		UserDetailsImpl userDetails;

		user = new ObjectMapper().readValue(request.getInputStream(),
				GoGreenUserEntity.class);
		userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(
				user.getEmail());

		return authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getEmail(),
						user.getPassword(), userDetails.getAuthorities()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req,
			HttpServletResponse res, FilterChain chain, Authentication auth)
			throws IOException {
		UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
		String token = JWT.create().withClaim("email", userDetails.getUsername())
				.withClaim("id", userDetails.getId())
				.withClaim("userDetailsId", userDetails.getUserDetailsId())
				.withClaim("userType", userDetails.getUserType().name())
				.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.withClaim("roles", new ArrayList<>(userDetails.getAuthorities()).stream()
						.map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.sign(Algorithm.HMAC512(SECRET.getBytes()));
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("email", userDetails.getUsername());
		responseMap.put("token", token);
		responseMap.put("userDetailsId", userDetails.getUserDetailsId());
		responseMap.put("userType", userDetails.getUserType().name());
		res.setStatus(HttpStatus.OK.value());
		res.setContentType(MediaType.APPLICATION_JSON_VALUE);

		objectMapper.writeValue(res.getWriter(), responseMap);
		res.getWriter().flush();
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException failed)
			throws IOException, ServletException {
		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put("message", "Invalid Credentials");

		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);

		objectMapper.writeValue(response.getWriter(), errorDetails);
		response.getWriter().flush();
	}
}

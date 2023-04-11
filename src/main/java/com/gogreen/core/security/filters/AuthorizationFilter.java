package com.gogreen.core.security.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gogreen.core.security.Constants;
import com.gogreen.models.auth.dtos.UserDetailsImpl;
import com.gogreen.models.auth.enums.UserTypeEnum;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Slf4j
public class AuthorizationFilter extends BasicAuthenticationFilter {
	public AuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader(Constants.HEADER_STRING);
		if (header == null || !header.startsWith(Constants.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}

		UsernamePasswordAuthenticationToken token = getAuthentication(request);

		if (token != null) {
			SecurityContextHolder.getContext().setAuthentication(token);
			chain.doFilter(request, response);
		}
		else {
			response.sendError(401);
		}
	}

	private UsernamePasswordAuthenticationToken getAuthentication(
			HttpServletRequest req) {
		String token = req.getHeader(Constants.HEADER_STRING);
		ArrayList<GrantedAuthority> authorities = new ArrayList<>();
		if (token != null) {
			DecodedJWT decJWT;
			try {
				decJWT = JWT.require(Algorithm.HMAC512(Constants.SECRET.getBytes()))
						.build().verify(token.replace(Constants.TOKEN_PREFIX, ""));
			}
			catch (Exception e) {
				return null;
			}

			String email = decJWT.getClaim("email").asString();
			List<String> arrayList = decJWT.getClaim("roles").asList(String.class);
			Long id = decJWT.getClaim("id").asLong();
			UserTypeEnum userTypeEnum = decJWT.getClaim("userType")
					.as(UserTypeEnum.class);

			if (email != null) {
				for (String authName : arrayList) {
					authorities.add(new SimpleGrantedAuthority(authName));
				}
				UserDetailsImpl userDetails = new UserDetailsImpl(id, email, null,
						userTypeEnum, new HashSet<>());
				return new UsernamePasswordAuthenticationToken(userDetails,
						token.replace(Constants.TOKEN_PREFIX, ""), authorities);

			}
		}
		return null;
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request)
			throws ServletException {
		String path = request.getRequestURI();
		log.info(path);

		return path.contains("/auth");
	}
}

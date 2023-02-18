package com.gogreen.core.security.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gogreen.core.security.Constants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthorizationFilter extends BasicAuthenticationFilter {
	public AuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
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
		} else {
			response.sendError(401);
		}
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req) {
		String token = req.getHeader(Constants.HEADER_STRING);
		ArrayList<GrantedAuthority> authorities = new ArrayList<>();
		if (token != null) {
			DecodedJWT decJWT = JWT.require(Algorithm.HMAC512(Constants.SECRET.getBytes())).build()
					.verify(token.replace(Constants.TOKEN_PREFIX, ""));
			String user = decJWT.getSubject();
			List<String> arrayList = decJWT.getClaim("Authorities").asList(String.class) ;

			if (user != null) {
				for(String authName: arrayList) {
					authorities.add(new SimpleGrantedAuthority(authName));
				}
				return new UsernamePasswordAuthenticationToken(user,token.replace(Constants.TOKEN_PREFIX, ""), authorities);

			}
		}
		return null;
	}


}

package com.gogreen.core.security;

import com.gogreen.core.security.filters.AuthenticationFilter;
import com.gogreen.core.security.filters.AuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Service
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private AuthenticationManager authManger;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private final UserDetailsService userDetailsService;

	private final AuthenticationConfiguration authenticationConfiguration;

	@Bean
	@Primary
	AuthenticationManager authenticationManager(
			AuthenticationConfiguration authenticationConfiguration) throws Exception {
		authManger = authenticationConfiguration.getAuthenticationManager();
		return authManger;
	}

	@Bean
	DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	PasswordEncoder passwordEncoder() {

		bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.cors().and().csrf().disable().authorizeRequests(
						(requests) -> requests.requestMatchers("/auth/**").permitAll()
								.requestMatchers("/user/**").hasRole("ADMIN").anyRequest()
								.authenticated()).sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilter(
				new AuthenticationFilter(bCryptPasswordEncoder, userDetailsService));
		http.addFilter(new AuthorizationFilter(
				authenticationManager(authenticationConfiguration)));
		return http.build();
	}

	@Bean
	WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("*");
			}
		};
	}

}

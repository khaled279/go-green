package com.gogreen.core.aop;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.gogreen.core.exception.SystemException;
import com.gogreen.core.exception.UserAlreadyExistsException;
import com.gogreen.core.exception.UserNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Configuration
@ControllerAdvice
@Slf4j
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	protected ResponseEntity handleUserNotFoundException(UserNotFoundException e) {
		Map errorMap = new HashMap();
		errorMap.put("errorMessage", "wrong email or password");
		errorMap.put("error code", "404");
		return ResponseEntity.status(404).body(errorMap);

	}

	@ExceptionHandler(SystemException.class)
	protected ResponseEntity handleSystemException(SystemException e) {
		Map errorMap = new HashMap();
		errorMap.put("errorMessage", e.getMessage());
		errorMap.put("error code", e.getHttpStatus().value());
		return ResponseEntity.status(e.getHttpStatus().value()).body(errorMap);

	}

	@ExceptionHandler(JWTVerificationException.class)
	protected ResponseEntity handleJWTVerificationException(JWTVerificationException e) {
		Map errorMap = new HashMap();
		errorMap.put("errorMessage", "Invalid Token");
		errorMap.put("error code", "401");
		return ResponseEntity.status(401).body(errorMap);
	}

	@ExceptionHandler(UserAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	protected ResponseEntity handleUserAlreadyExistsException(
			UserAlreadyExistsException e) {
		Map errorMap = new HashMap();
		errorMap.put("errorMessage", "user already exists");
		errorMap.put("error code", "409");

		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMap);

	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	ResponseEntity handleUnhandledException(Exception e) {
		log.error("Exception was thrown:", e);
		return ResponseEntity.status(500).build();
	}
}

package com.gogreen.core.aop;

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

@Aspect
@Configuration
@ControllerAdvice
@Slf4j
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	protected ResponseEntity<UserNotFoundException> handleUserNotFoundException(
			EntityNotFoundException e) {
		UserNotFoundException userNotFoundException = new UserNotFoundException();

		return new ResponseEntity<>(userNotFoundException,
				userNotFoundException.getHttpStatus());

	}

	@ExceptionHandler(UserAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	protected ResponseEntity<UserAlreadyExistsException> handleUserAlreadyExistsException(
			EntityNotFoundException e) {
		UserAlreadyExistsException userAlreadyExistsException = new UserAlreadyExistsException();
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(userAlreadyExistsException);

	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	ResponseEntity handleUnhandledException(Exception e) {
		log.error("Exception was thrown:", e);
		return ResponseEntity.status(500).build();
	}
}

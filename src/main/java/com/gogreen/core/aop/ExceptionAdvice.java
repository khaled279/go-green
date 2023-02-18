package com.gogreen.core.aop;

import com.gogreen.core.exception.UserAlreadyExistsException;
import com.gogreen.core.exception.UserNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Aspect
@Configuration
@ControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	protected ResponseEntity<UserNotFoundException> handleUserNotFoundException(
			EntityNotFoundException e){
		UserNotFoundException userNotFoundException = new UserNotFoundException();

		return new ResponseEntity<>(userNotFoundException,userNotFoundException.getHttpStatus());

	}
	@ExceptionHandler(UserAlreadyExistsException.class)
	protected ResponseEntity<UserAlreadyExistsException> handleUserAlreadyExistsException(
			EntityNotFoundException e){
		UserAlreadyExistsException userAlreadyExistsException = new UserAlreadyExistsException();
		return new ResponseEntity<>(userAlreadyExistsException,UserAlreadyExistsException.HTTP_STATUS);

	}


}

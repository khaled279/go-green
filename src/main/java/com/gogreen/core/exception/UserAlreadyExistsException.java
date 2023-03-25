package com.gogreen.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Setter
@Getter
@ResponseStatus(code = HttpStatus.CONFLICT, reason = "User not found")
public class UserAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public final HttpStatus httpStatus = HttpStatus.CONFLICT;

	public static final String MESSAGE = "USER WITH SAME EMAIL ALREADY EXISTS PLEASE LOGIN";

}

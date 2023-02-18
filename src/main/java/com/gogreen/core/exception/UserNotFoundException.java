package com.gogreen.core.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Setter
@Getter
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User not found")
public class UserNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private final HttpStatus httpStatus = HttpStatus.NOT_FOUND;
	private final String message ="User not found";

}

package com.gogreen.core.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class SystemException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private HttpStatus httpStatus;

	private String message;

	public SystemException(HttpStatus httpStatus, String message) {
		this.message = message;
		this.httpStatus = httpStatus;
	}

	public SystemException() {

	}
}

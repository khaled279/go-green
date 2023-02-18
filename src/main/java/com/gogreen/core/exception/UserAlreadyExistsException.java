package com.gogreen.core.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public static final HttpStatus HTTP_STATUS = HttpStatus.CONFLICT;

	public static final String MESSAGE = "USER WITH SAME EMAIL ALREADY EXISTS PLEASE LOGIN";

}

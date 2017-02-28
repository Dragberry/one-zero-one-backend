package org.dragberry.ozo.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "There is no user with such ID!")
public class UserNotFountException extends RuntimeException {

	private static final long serialVersionUID = -4831400170514120448L;
	
	public UserNotFountException() {}
	
	public UserNotFountException(String msg) {
		super(msg);
	}
	
	public UserNotFountException(String msg, Throwable exception) {
		super(msg, exception);
	}

}

package org.dragberry.ozo.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "An error has occured during user creation")
public class UserCreationError extends RuntimeException {

	private static final long serialVersionUID = 7042632574266870016L;

	public UserCreationError() {}
}

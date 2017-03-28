package org.dragberry.ozo.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.GONE, reason = "Application version is obsolete!")
public class WrongAppVersionException extends RuntimeException {

	private static final long serialVersionUID = -1919184423912209532L;

}

package org.dragberry.ozo.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "There is no level with such ID!")
public class LevelNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5674257769272255929L;
	
	public LevelNotFoundException() {}
	
	public LevelNotFoundException(String msg) {
		super(msg);
	}
	
	public LevelNotFoundException(String msg, Throwable exception) {
		super(msg, exception);
	}

}

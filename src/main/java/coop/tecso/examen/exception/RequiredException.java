package coop.tecso.examen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RequiredException extends RuntimeException {

	private static final long serialVersionUID = 2840131817109240301L;
	
	public RequiredException(String message) {
		super(message);
	}
}

package coop.tecso.examen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class MethodNotAllowedException extends RuntimeException {

	private static final long serialVersionUID = 2840131817109240301L;
	
	public MethodNotAllowedException(String message) {
		super(message);
	}
}

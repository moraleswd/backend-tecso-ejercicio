package coop.tecso.examen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class OverdraftException extends RuntimeException {

	private static final long serialVersionUID = 2840131817109240301L;
	
	public OverdraftException(String message) {
		super(message);
	}
}

package coop.tecso.examen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 2840131817109240301L;
	
	public BusinessException(String message) {
		super(message);
	}
}

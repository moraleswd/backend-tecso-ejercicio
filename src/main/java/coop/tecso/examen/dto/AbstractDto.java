package coop.tecso.examen.dto;

import java.io.Serializable;

public class AbstractDto implements Serializable {

	private static final long serialVersionUID = 4554360788178567657L;

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}

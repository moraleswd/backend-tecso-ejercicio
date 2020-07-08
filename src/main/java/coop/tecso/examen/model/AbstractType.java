package coop.tecso.examen.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractType extends AbstractPersistentObject {

	private static final long serialVersionUID = 3622932955280981546L;

	@Column(length=3, unique=true, nullable=false)
	private String code;
	
	@Column(length=100)
	private String name;
	
	@Column(length=200)
	private String description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}

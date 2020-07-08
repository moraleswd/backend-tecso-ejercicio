package coop.tecso.examen.dto;

public class MovementTypeDto extends AbstractDto {

	private static final long serialVersionUID = 4678986701433197924L;

	private String code;
	
	private String name;
	
	private String description;

	private OperationTypeDto operationType;
	
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

	public OperationTypeDto getOperationType() {
		return operationType;
	}

	public void setOperationType(OperationTypeDto operationType) {
		this.operationType = operationType;
	}

}

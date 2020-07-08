package coop.tecso.examen.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="movement_type")
public class MovementType extends AbstractType {

	private static final long serialVersionUID = -7126248824158399417L;

	@ManyToOne
	@JoinColumn(name="operation_type_id", nullable=false)
	private OperationType operationType;

	public OperationType getOperationType() {
		return operationType;
	}

	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}
}

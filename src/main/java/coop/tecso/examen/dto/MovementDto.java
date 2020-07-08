package coop.tecso.examen.dto;

import java.math.BigDecimal;
import java.util.Date;

public class MovementDto extends AbstractDto {

	private static final long serialVersionUID = 607908400630715263L;

	private Date movementDate;

	private MovementTypeDto movementType;
	
	private CurrentAccountDto currentAccount;
	
	private String description;
	
	private BigDecimal amount;

	public Date getMovementDate() {
		return movementDate;
	}

	public void setMovementDate(Date movementDate) {
		this.movementDate = movementDate;
	}
	
	public MovementTypeDto getMovementType() {
		return movementType;
	}

	public void setMovementType(MovementTypeDto MovementType) {
		this.movementType = MovementType;
	}

	public CurrentAccountDto getCurrentAccount() {
		return currentAccount;
	}

	public void setCurrentAccount(CurrentAccountDto currentAccount) {
		this.currentAccount = currentAccount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}

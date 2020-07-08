package coop.tecso.examen.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="movement")
public class Movement extends AbstractPersistentObject {

	private static final long serialVersionUID = -7598197211878407654L;
	
	@Column(name="movement_date", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date movementDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="movement_type_id", nullable=false)
	private MovementType movementType;
	
	@ManyToOne
	@JoinColumn(name="current_account_id", nullable=false)
	private CurrentAccount currentAccount;
	
	@Column(length=200, nullable=false)
	private String description;
	
	@Column(scale=2, nullable=false)
	private BigDecimal amount;

	public Date getMovementDate() {
		return movementDate;
	}

	public void setMovementDate(Date movementDate) {
		this.movementDate = movementDate;
	}

	public MovementType getMovementType() {
		return movementType;
	}

	public void setMovementType(MovementType movementType) {
		this.movementType = movementType;
	}

	public CurrentAccount getCurrentAccount() {
		return currentAccount;
	}

	public void setCurrentAccount(CurrentAccount currentAccount) {
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

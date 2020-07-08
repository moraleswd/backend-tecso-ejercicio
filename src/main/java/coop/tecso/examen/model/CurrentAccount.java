package coop.tecso.examen.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "current_account")
public class CurrentAccount extends AbstractPersistentObject {

	private static final long serialVersionUID = -5900688422777449686L;

	@Column(name="account_number", unique=true, nullable=false, length=50 )
	private String accountNumber;
	
	@Column(scale=2, nullable=false)
	private BigDecimal balance;
	
	@OneToOne
    @JoinColumn(name = "currency_type_id", referencedColumnName = "id", nullable=false)
	private CurrencyType currencyType;
 
	@OneToMany(mappedBy="currentAccount", fetch=FetchType.LAZY)
	private List<Movement> movements;
	
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}

	public List<Movement> getMovements() {
		return movements;
	}

	public void setMovements(List<Movement> movements) {
		this.movements = movements;
	}
}

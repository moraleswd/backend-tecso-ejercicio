package coop.tecso.examen.dto;

import java.math.BigDecimal;

public class CurrentAccountDto extends AbstractDto {

	private static final long serialVersionUID = 8567548611574172664L;

	private String accountNumber;
	
	private BigDecimal balance;
	
	private CurrencyTypeDto currencyType;
	
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

	public CurrencyTypeDto getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CurrencyTypeDto currencyType) {
		this.currencyType = currencyType;
	}
}

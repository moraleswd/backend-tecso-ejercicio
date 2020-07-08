package coop.tecso.examen.service.impl;

import java.math.BigDecimal;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import coop.tecso.examen.dto.CurrentAccountDto;
import coop.tecso.examen.exception.OverdraftException;
import coop.tecso.examen.service.CurrentAccountBusinessService;
import coop.tecso.examen.service.CurrentAccountService;
import coop.tecso.examen.utils.Constants;
import coop.tecso.examen.utils.CurrencyConstants;

@Service("currentAccountBusinessService")
public class CurrentAccountBusinessServiceImpl implements CurrentAccountBusinessService {

	@Autowired
	private CurrentAccountService currentAccountService;

	@Override
	@Transactional
	public CurrentAccountDto updateBalance(Long idAccount, BigDecimal amount, String operationCode) throws Exception {
		CurrentAccountDto currentAccount = currentAccountService.findById(idAccount);
		BigDecimal balanceUpdated = null;
		if(currentAccount == null) {
			throw new EntityNotFoundException("Cuenta inexistente");
		}
		//Si es una operacion de tipo debito
		if(operationCode.equals(Constants.OPERATION_DEBIT_CODE)) {
			//Valida el limite posible de saldo en la cuenta en caso de descubierto 
			if(validateOverdraft(currentAccount, amount)) {
				//Se resta el monto a el saldo de la cuenta
				balanceUpdated = currentAccount.getBalance().subtract(amount);
			}else {
				throw new OverdraftException("La cuenta excedió el limite por descubierto");
			}
		//Si la operacion es de tipo credito
		}else {
			balanceUpdated = currentAccount.getBalance().add(amount);
		}
		currentAccount.setBalance(balanceUpdated);
		return currentAccountService.save(currentAccount);
	}

	private Boolean validateOverdraft(CurrentAccountDto account, BigDecimal amount) {
		Boolean isValid = true;
		switch (account.getCurrencyType().getCode()) {
		case CurrencyConstants.USD:
			if((account.getBalance().subtract(amount)).compareTo(BigDecimal.valueOf(Constants.MAX_OVERDRAFT_USD)) == -1) {
				isValid = false;
			}	
			break;
		case CurrencyConstants.EUR:
			if((account.getBalance().subtract(amount)).compareTo(BigDecimal.valueOf(Constants.MAX_OVERDRAFT_EUR)) == -1) {
				isValid = false;
			}	
			break;
		case CurrencyConstants.ARS:
			if((account.getBalance().subtract(amount)).compareTo(BigDecimal.valueOf(Constants.MAX_OVERDRAFT_ARS)) == -1) {
				isValid = false;
			}	
			break;
		default:
			break;
		}
		return isValid;
	}

}

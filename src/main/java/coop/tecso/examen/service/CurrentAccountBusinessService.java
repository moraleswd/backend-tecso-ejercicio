package coop.tecso.examen.service;

import java.math.BigDecimal;

import coop.tecso.examen.dto.CurrentAccountDto;

public interface CurrentAccountBusinessService {

	CurrentAccountDto updateBalance(Long idAccount, BigDecimal amount, String operationCode) throws Exception;
	
}

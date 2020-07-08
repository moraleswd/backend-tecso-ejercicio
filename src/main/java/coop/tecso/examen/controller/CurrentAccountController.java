package coop.tecso.examen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import coop.tecso.examen.dto.CurrentAccountDto;
import coop.tecso.examen.model.CurrentAccount;
import coop.tecso.examen.service.CurrentAccountService;
import coop.tecso.examen.service.GenericPersistenceService;

@RestController
@RequestMapping("/currentAccount")
public class CurrentAccountController extends GenericController<CurrentAccount, CurrentAccountDto> {
	
	@Autowired
	private CurrentAccountService currentAccountService;

	@Override
	protected GenericPersistenceService<CurrentAccount, CurrentAccountDto> getPersistenceService() {
		return currentAccountService;
	}
	
}

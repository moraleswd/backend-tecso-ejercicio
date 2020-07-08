package coop.tecso.examen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import coop.tecso.examen.dto.CurrencyTypeDto;
import coop.tecso.examen.model.CurrencyType;
import coop.tecso.examen.service.CurrencyTypeService;
import coop.tecso.examen.service.GenericPersistenceService;

@RestController
@RequestMapping("/currencyType")
public class CurrencyTypeController extends GenericController<CurrencyType, CurrencyTypeDto> {
	
	@Autowired
	private CurrencyTypeService currencyTypeService;

	@Override
	protected GenericPersistenceService<CurrencyType, CurrencyTypeDto> getPersistenceService() {
		return currencyTypeService;
	}
	
}

package coop.tecso.examen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import coop.tecso.examen.dto.CurrencyTypeDto;
import coop.tecso.examen.model.CurrencyType;
import coop.tecso.examen.repository.CurrencyTypeRepository;
import coop.tecso.examen.service.CurrencyTypeService;

@Service("currencyTypeService")
public class CurrencyTypeServiceImpl extends GenericPersistenceServiceImpl<CurrencyType, CurrencyTypeDto> implements CurrencyTypeService {

	@Autowired
	private CurrencyTypeRepository currencyTypeRepository;
	
	@Override
	protected JpaRepository<CurrencyType, Long> getRepository() {
		return currencyTypeRepository;
	}

}

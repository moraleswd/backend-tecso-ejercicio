package coop.tecso.examen.service;

import java.util.List;

import coop.tecso.examen.dto.AbstractDto;
import coop.tecso.examen.model.AbstractPersistentObject;

public interface GenericPersistenceService  <P extends AbstractPersistentObject, D extends AbstractDto>{

	D convertToDto(P po);

	P convertToModel(D dto);
	
	List<D> findAll();
	
	D save(D dto) throws Exception;
	
	void deleteById(Long id) throws Exception;
	
	D findById(Long id) throws Exception;
}

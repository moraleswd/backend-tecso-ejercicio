package coop.tecso.examen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import coop.tecso.examen.dto.MovementTypeDto;
import coop.tecso.examen.model.MovementType;
import coop.tecso.examen.repository.MovementTypeRepository;
import coop.tecso.examen.service.MovementTypeService;

@Service("movementTypeService")
public class MovementTypeServiceImpl extends GenericPersistenceServiceImpl<MovementType, MovementTypeDto> implements MovementTypeService {

	@Autowired
	private MovementTypeRepository movementTypeRepository;
	
	@Override
	protected JpaRepository<MovementType, Long> getRepository() {
		return movementTypeRepository;
	}

}

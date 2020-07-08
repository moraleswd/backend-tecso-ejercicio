package coop.tecso.examen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import coop.tecso.examen.dto.MovementTypeDto;
import coop.tecso.examen.model.MovementType;
import coop.tecso.examen.service.GenericPersistenceService;
import coop.tecso.examen.service.MovementTypeService;

@RestController
@RequestMapping("/movementType")
public class MovementTypeController extends GenericController<MovementType, MovementTypeDto> {
	
	@Autowired
	private MovementTypeService movementTypeService;

	@Override
	protected GenericPersistenceService<MovementType, MovementTypeDto> getPersistenceService() {
		return movementTypeService;
	}
	
}

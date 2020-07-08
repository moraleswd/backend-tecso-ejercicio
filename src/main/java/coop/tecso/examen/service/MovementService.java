package coop.tecso.examen.service;

import java.util.List;

import coop.tecso.examen.dto.MovementDto;
import coop.tecso.examen.model.Movement;

public interface MovementService extends GenericPersistenceService<Movement, MovementDto>{

	List<MovementDto> findByCurrentAccountOrderByMovementDateDesc(Long idAccount);
}

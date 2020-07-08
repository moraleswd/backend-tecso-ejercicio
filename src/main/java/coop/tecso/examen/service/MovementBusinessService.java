package coop.tecso.examen.service;

import coop.tecso.examen.dto.MovementDto;

public interface MovementBusinessService {

	MovementDto addMovement(MovementDto movement) throws Exception;
}

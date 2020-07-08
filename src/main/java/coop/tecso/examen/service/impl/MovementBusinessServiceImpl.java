package coop.tecso.examen.service.impl;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import coop.tecso.examen.dto.MovementDto;
import coop.tecso.examen.exception.RequiredException;
import coop.tecso.examen.service.CurrentAccountBusinessService;
import coop.tecso.examen.service.MovementBusinessService;
import coop.tecso.examen.service.MovementService;

@Service("movementBusinessService")
public class MovementBusinessServiceImpl implements MovementBusinessService {

	@Autowired
	private MovementService movementService;
	
	@Autowired
	private CurrentAccountBusinessService currentAccountBusinessService;
	
	@Override
	@Transactional
	public MovementDto addMovement(MovementDto movement) throws Exception {
		//Cargo la fecha con horario UTC
		movement.setMovementDate(Date.from(Instant.now()));
		if(movement.getMovementType() == null) {
			throw new RequiredException("El tipo de movimiento es requerido");
		}
		if(movement.getMovementType().getOperationType() == null) {
			throw new RequiredException("El tipo de operación es requerido");
		}
		if(movement.getCurrentAccount() == null) {
			throw new RequiredException("El movimiento no tiene una cuenta asociada");
		}
		
		currentAccountBusinessService.updateBalance(movement.getCurrentAccount().getId(), movement.getAmount(), movement.getMovementType().getOperationType().getCode());
		
		
		return movementService.save(movement);
	}

}

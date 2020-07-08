package coop.tecso.examen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import coop.tecso.examen.dto.MovementDto;
import coop.tecso.examen.exception.MethodNotAllowedException;
import coop.tecso.examen.model.Movement;
import coop.tecso.examen.service.GenericPersistenceService;
import coop.tecso.examen.service.MovementBusinessService;
import coop.tecso.examen.service.MovementService;
import coop.tecso.examen.utils.Constants;

@RestController
@RequestMapping("/movement")
public class MovementController
		extends GenericController<Movement, MovementDto> {

	@Autowired
	private MovementService movementService;

	@Autowired
	private MovementBusinessService movementBusinessService;

	@Override
	protected GenericPersistenceService<Movement, MovementDto> getPersistenceService() {
		return movementService;
	}

	@PostMapping("/save")
	@Override
	protected @ResponseBody ResponseEntity<MovementDto> save(@RequestBody MovementDto dto)
			throws Exception {
		//La modificacion no esta permitida para esta entidad
		if (dto != null && dto.getId() != null) {
			throw new MethodNotAllowedException(Constants.METHOD_NOT_ALLOWED_MSG);
		}
		MovementDto movementAdded = movementBusinessService.addMovement(dto);
		return new ResponseEntity<MovementDto>(movementAdded, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	@Override
	protected ResponseEntity<Void> deleteById(@PathVariable Long id) {
		//El borrado no esta permitido para esta entidad
		throw new MethodNotAllowedException(Constants.METHOD_NOT_ALLOWED_MSG);
	}
	
	@GetMapping("/findMovementsByAccount/{idAccount}")
	protected @ResponseBody ResponseEntity<List<MovementDto>> findMovementsByAccount(@PathVariable Long idAccount)
			throws Exception {
		List<MovementDto> result = movementService.findByCurrentAccountOrderByMovementDateDesc(idAccount);
		return new ResponseEntity<List<MovementDto>>(result, HttpStatus.OK);
	}
}

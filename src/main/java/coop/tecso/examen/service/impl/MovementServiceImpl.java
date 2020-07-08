package coop.tecso.examen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import coop.tecso.examen.dto.MovementDto;
import coop.tecso.examen.exception.MethodNotAllowedException;
import coop.tecso.examen.model.Movement;
import coop.tecso.examen.repository.MovementRepository;
import coop.tecso.examen.service.MovementService;
import coop.tecso.examen.utils.Constants;

@Service("movementService")
public class MovementServiceImpl extends GenericPersistenceServiceImpl<Movement, MovementDto> implements MovementService {

	@Autowired
	private MovementRepository movementRepository;
	 
	@Override
	protected JpaRepository<Movement, Long> getRepository() {
		return movementRepository;
	}

	@Override
	@Transactional
	public MovementDto save(MovementDto dto) throws Exception {
		if(dto != null && dto.getId() != null) {
			throw new MethodNotAllowedException(Constants.METHOD_NOT_ALLOWED_MSG);
		}
		return super.save(dto);
	}
	
	@Override
	public void deleteById(Long id) throws Exception {
		throw new MethodNotAllowedException(Constants.METHOD_NOT_ALLOWED_MSG);
	}

	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public List<MovementDto> findByCurrentAccountOrderByMovementDateDesc(
			Long idAccount) {
		List<Movement> result = movementRepository.findByCurrentAccountIdOrderByMovementDateDesc(idAccount);
		return super.convertToDto(result);
	}
	
}

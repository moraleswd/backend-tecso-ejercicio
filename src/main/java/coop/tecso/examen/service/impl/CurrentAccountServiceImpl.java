package coop.tecso.examen.service.impl;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import coop.tecso.examen.dto.CurrentAccountDto;
import coop.tecso.examen.exception.BusinessException;
import coop.tecso.examen.model.CurrentAccount;
import coop.tecso.examen.repository.CurrentAccountRepository;
import coop.tecso.examen.service.CurrentAccountService;

@Service("currentAccountService")
public class CurrentAccountServiceImpl extends GenericPersistenceServiceImpl<CurrentAccount, CurrentAccountDto> implements CurrentAccountService {

	@Autowired
	private CurrentAccountRepository currentAccountRepository;
	
	@Override
	protected JpaRepository<CurrentAccount, Long> getRepository() {
		return currentAccountRepository;
	}
	
	@Override
	@Transactional
	public CurrentAccountDto save(CurrentAccountDto dto) throws Exception {
		if(dto != null && dto.getId() == null) {
			dto.setBalance(BigDecimal.valueOf(0));
		}else {
			Optional<CurrentAccount> caFounded = currentAccountRepository.findById(dto.getId());
			//Si tiene movimientos
			if(caFounded.isPresent() && (caFounded.get().getMovements() != null && !caFounded.get().getMovements().isEmpty())) {
				//Si cambio la moneda
				if(!(dto.getCurrencyType().getId().equals(caFounded.get().getCurrencyType().getId()))) {
					throw new BusinessException("No es posible modificar la moneda de la cuenta, la misma contiene movimientos.");
				}
			}
		}
		return super.save(dto);
	}

	@Override
	public void deleteById(Long id) throws Exception {
		Optional<CurrentAccount> caFounded = currentAccountRepository.findById(id);
		if(caFounded.isPresent() && (caFounded.get().getMovements() != null && !caFounded.get().getMovements().isEmpty())) {
			throw new BusinessException("No es posible eliminar la cuenta, la misma contiene movimientos.");
		}else {
			super.deleteById(id);
		}
	}
}

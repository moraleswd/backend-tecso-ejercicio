package coop.tecso.examen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import coop.tecso.examen.model.CurrencyType;

public interface CurrencyTypeRepository extends JpaRepository<CurrencyType, Long> {

}

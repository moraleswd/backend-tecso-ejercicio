package coop.tecso.examen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import coop.tecso.examen.model.MovementType;

public interface MovementTypeRepository extends JpaRepository<MovementType, Long> {

}

package coop.tecso.examen.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import coop.tecso.examen.dto.AbstractDto;
import coop.tecso.examen.model.AbstractPersistentObject;
import coop.tecso.examen.service.GenericPersistenceService;

public abstract class GenericController<P extends AbstractPersistentObject, D extends AbstractDto> {

	protected abstract GenericPersistenceService<P, D> getPersistenceService();
	
	@GetMapping("/findAll")
	protected ResponseEntity<List<D>> findAll() {
		List<D> result = this.getPersistenceService().findAll();
		return new ResponseEntity<List<D>>(result, HttpStatus.OK);
	}
	
	@PostMapping("/save")
	protected @ResponseBody ResponseEntity<D> save(@RequestBody D dto) throws Exception {
		D result = this.getPersistenceService().save(dto);
		return new ResponseEntity<D>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	protected ResponseEntity<Void> deleteById(@PathVariable Long id) throws Exception {
		this.getPersistenceService().deleteById(id);
		return ResponseEntity.noContent().build();
	}
}

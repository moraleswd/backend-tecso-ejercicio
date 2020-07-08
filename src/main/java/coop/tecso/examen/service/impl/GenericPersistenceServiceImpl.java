package coop.tecso.examen.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import coop.tecso.examen.dto.AbstractDto;
import coop.tecso.examen.model.AbstractPersistentObject;
import coop.tecso.examen.service.GenericPersistenceService;

public abstract class GenericPersistenceServiceImpl <P extends AbstractPersistentObject, D extends AbstractDto> implements GenericPersistenceService<P,D> {

    @Autowired
    private ModelMapper modelMapper;
    
	@SuppressWarnings("unchecked")
	private Class<D> getDtoClass() {
    	ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<D>) parameterizedType.getActualTypeArguments()[1];
    }
	
	@SuppressWarnings("unchecked")
	private Class<P> getModelClass() {
    	ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<P>) parameterizedType.getActualTypeArguments()[0];
    }
	
	public D convertToDto(P po) {
	    D dto = modelMapper.map(po, getDtoClass());
	    return dto;
	}

	public P convertToModel(D dto) {
	    P model = modelMapper.map(dto, getModelClass());
	    return model;
	}
	
	public List<D> convertToDto(List<P> pos)  {
        return pos.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
	}
	
	public List<P> convertToModel(List<D> dtos) {
        return dtos.stream()
                .map(this::convertToModel)
                .collect(Collectors.toList());
	}
	
	protected ModelMapper getModelMapper() {
		return modelMapper;
	}
	
	protected abstract JpaRepository<P, Long> getRepository();
	
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public List<D> findAll(){
		List<D> result = null;
		List<P> modelFounded = this.getRepository().findAll();
		if(modelFounded != null && !modelFounded.isEmpty()) {
			result = convertToDto(modelFounded);
		}
		return result;
	}
	
	@Transactional
	public D save(D dto) throws Exception {
		P modelSaved = this.getRepository().save(convertToModel(dto));
		return convertToDto(modelSaved);
	}
	
	@Transactional
	public void deleteById(Long id) throws Exception {
		this.getRepository().deleteById(id);
	}
	
	
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public D findById(Long id) throws Exception {
		D result = null;
		Optional<P> modelFounded = this.getRepository().findById(id);
		if(modelFounded.isPresent()) {
			result = convertToDto(modelFounded.get());
		}
		return result;
	}
}

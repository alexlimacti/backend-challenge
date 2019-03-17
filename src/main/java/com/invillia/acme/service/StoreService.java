package com.invillia.acme.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.invillia.acme.domain.Address;
import com.invillia.acme.domain.Store;
import com.invillia.acme.dto.StoreDTO;
import com.invillia.acme.dto.StoreNewDTO;
import com.invillia.acme.repository.AddressRepository;
import com.invillia.acme.repository.StoreRepository;
import com.invillia.acme.service.exceptions.DataIntegrityException;
import com.invillia.acme.service.exceptions.ObjectNotFoundException;

@Service
public class StoreService {
	
	@Autowired
	private StoreRepository storeRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	public Store find(Long id) {
		Optional<Store> obj = storeRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object don't found! Id: " + id + ", Type: " + Store.class.getName()));
	}

	@Transactional
	public Store insert(Store entity) {
		entity.setId(null);
		entity = storeRepository.save(entity);
		addressRepository.save(entity.getAddress());
		return entity;
	}
	
	public Store update(Store entity) {
		Store newEntity = find(entity.getId());
		updateData(newEntity, entity);
		return storeRepository.save(newEntity);
	}
	
	public void delete(Long id) {
		find(id);
		try {
			storeRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Can't delete because there're related requests");
		}
	}
	
	public Store fromDTO(StoreDTO objDto) {		
		return new Store(objDto.getName(), null);
	}
	
	public Store fromNewDTO(StoreNewDTO objDto) {
		Address a = new Address(objDto.getStreet(),objDto.getState(),objDto.getCity(),objDto.getDistrict(),objDto.getCountry(),objDto.getNumber());
		return new Store(objDto.getName(), a);
	}
	
	public Page<Store> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return storeRepository.findAll(pageRequest);
	}
	
	private void updateData(Store newObj, Store obj) {
		newObj.setName(obj.getName());
	}
	
	public List<Store> findAll() {
		return storeRepository.findAll();
	}
	

}
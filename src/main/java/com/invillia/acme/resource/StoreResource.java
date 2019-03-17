package com.invillia.acme.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.invillia.acme.domain.Store;
import com.invillia.acme.dto.StoreDTO;
import com.invillia.acme.dto.StoreNewDTO;
import com.invillia.acme.service.StoreService;

@RestController
@RequestMapping("/store")
public class StoreResource {

	@Autowired
	private StoreService storeService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<StoreDTO>> findAll() {
		List<Store> list = storeService.findAll();
		List<StoreDTO> listDto = list.stream().map(obj -> new StoreDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Store> find(@PathVariable Long id) {
		Store obj = storeService.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody StoreNewDTO objDTO) {
		Store entity = storeService.fromNewDTO(objDTO);
		entity = storeService.insert(entity);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody StoreDTO objDTO, @PathVariable Long id) {
		Store obj = storeService.fromDTO(objDTO);
		obj.setId(id);
		obj = storeService.update(obj);
		return ResponseEntity.noContent().build();
	}

}

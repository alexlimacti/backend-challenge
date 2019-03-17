package com.invillia.acme.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.invillia.acme.domain.Store;
import com.invillia.acme.dto.StoreNewDTO;
import com.invillia.acme.repository.StoreRepository;
import com.invillia.acme.resource.exceptions.FieldMessage;

public class StoreInsertValidator implements ConstraintValidator<StoreInsert, StoreNewDTO> {
	
	@Autowired
	private StoreRepository repo;
	
	@Override
	public void initialize(StoreInsert ann) {
	}

	@Override
	public boolean isValid(StoreNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		Store aux = repo.findByName(objDto.getName());
		if(aux != null) {
			list.add(new FieldMessage("Name","Already existing name."));
		}
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}

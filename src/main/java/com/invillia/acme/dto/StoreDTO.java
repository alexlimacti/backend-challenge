package com.invillia.acme.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.invillia.acme.domain.Store;
import com.invillia.acme.service.validation.StoreUpdate;

@StoreUpdate
public class StoreDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotEmpty(message = "Required field")
	@Length(min = 5, max = 120, message = "Size must be between 5 and 120 characters")
	private String name;

	public StoreDTO() {

	}
	
	public StoreDTO(Store entity) {
		id = entity.getId();
		name = entity.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

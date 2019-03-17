package com.invillia.acme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.invillia.acme.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
	
	@Transactional(readOnly=true)
	public Store findByName(String name);

}

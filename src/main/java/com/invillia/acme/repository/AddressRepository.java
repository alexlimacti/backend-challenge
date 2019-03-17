package com.invillia.acme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invillia.acme.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}

package com.invillia.acme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invillia.acme.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}

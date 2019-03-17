package com.invillia.acme.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.invillia.acme.domain.Order;
import com.invillia.acme.domain.enumerator.OrderStatus;
import com.invillia.acme.domain.enumerator.PaymentStatus;
import com.invillia.acme.repository.AddressRepository;
import com.invillia.acme.repository.OrderItemRepository;
import com.invillia.acme.repository.OrderRepository;
import com.invillia.acme.repository.PaymentRepository;
import com.invillia.acme.service.exceptions.ObjectNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private AddressRepository addressRepository;


	public Order find(Long id) {
		Optional<Order> obj = orderRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object don't found! Id: " + id + ", Type: " + Order.class.getName()));
	}

	public Order insert(Order entity) {
		addressRepository.save(entity.getAddress());
		entity.setId(null);
		entity.setConfirmationDate(new Date());
		entity.getPayment().setPaymentStatus(PaymentStatus.waiting);
		entity.getPayment().setOrder(entity);
		entity = orderRepository.save(entity);
		paymentRepository.save(entity.getPayment());
		orderItemRepository.saveAll(entity.getItems());
		return entity;
	}

	public Order updatePaymentStatus(Order entity) {
		entity.getPayment().setPaymentStatus(PaymentStatus.confirmed);
		paymentRepository.save(entity.getPayment());
		return entity;
	}

	public Order refunded(Order entity) {
		if(differenceBetweenDates(entity.getConfirmationDate(), new Date()) >= 10 ||
				!entity.getStatus().equals(OrderStatus.confirmed)) {
			throw new IllegalArgumentException("Sorry! Can not reverse order!");
		}
		entity.setStatus(OrderStatus.refunded);
		entity.getPayment().setPaymentStatus(PaymentStatus.refunded);
		return orderRepository.save(entity);
	}

	public Page<Order> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return orderRepository.findAll(pageRequest);
	}

	public Long differenceBetweenDates(Date beginDate, Date endDate) {

		long diffInMillies = Math.abs(endDate.getTime() - beginDate.getTime());
		long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

		return diff;
	}
	
	public List<Order> findAll() {
		return orderRepository.findAll();
	}

}

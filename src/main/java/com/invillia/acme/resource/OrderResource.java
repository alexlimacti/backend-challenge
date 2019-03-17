package com.invillia.acme.resource;

import java.net.URI;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.invillia.acme.domain.Order;
import com.invillia.acme.domain.enumerator.OrderStatus;
import com.invillia.acme.domain.enumerator.PaymentStatus;
import com.invillia.acme.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderResource {

	@Autowired
	private OrderService orderService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Order>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "confirmationDate") String orderBy,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction) {
		Page<Order> list = orderService.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Order> find(@PathVariable Long id) {
		Order entity = orderService.find(id);
		return ResponseEntity.ok().body(entity);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Order entity) {
		entity = orderService.insert(entity);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "confirmpayment/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> confirmPayment(@Valid @RequestBody Order entity) {
		entity.getPayment().setPaymentStatus(PaymentStatus.confirmed);
		entity.setConfirmationDate(new Date());
		entity.setStatus(OrderStatus.confirmed);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "refunded/{id}", method = RequestMethod.GET)
	public ResponseEntity<Order> refunded(@PathVariable Long id) {
		Order entity = orderService.find(id);
		return ResponseEntity.ok().body(orderService.refunded(entity));
	}

}

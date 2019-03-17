package com.invillia.acme.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.invillia.acme.domain.enumerator.OrderStatus;
import com.invillia.acme.domain.enumerator.PaymentStatus;

@Entity
@Table(name="orders")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@JsonDeserialize
	@ManyToOne
	private Address address;

	@Temporal(TemporalType.DATE)
	private Date confirmationDate;

	private Integer status;

	@JsonSerialize
	@OneToMany(cascade = {CascadeType.ALL})
	@JoinColumn(name = "order_id")
	private Set<OrderItem> items = new HashSet<>();

	@JsonDeserialize
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
	private Payment payment;

	public Order() {

	}

	public Order(Address address, Date confirmationDate, OrderStatus status) {
		super();
		this.address = address;
		this.confirmationDate = confirmationDate;
		this.status = (status == null) ? null : status.getCod();
	}

	public Double getAmount() {
		double sum = 0.0;
		for (OrderItem oi : items) {
			sum = sum + oi.getSubTotal();
		}
		return sum;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Date getConfirmationDate() {
		return confirmationDate;
	}

	public void setConfirmationDate(Date confirmationDate) {
		this.confirmationDate = confirmationDate;
	}

	public OrderStatus getStatus() {
		return OrderStatus.toEnum(status);
	}

	public void setStatus(OrderStatus status) {
		this.status = status.getCod();
	}

	public Set<OrderItem> getItems() {
		return items;
	}

	public void setItems(Set<OrderItem> items) {
		this.items = items;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		StringBuilder builder = new StringBuilder();
		builder.append("Order number: ");
		builder.append(getId());
		builder.append(", Confirmation Date: ");
		builder.append(sdf.format(getConfirmationDate()));
		builder.append(", Order Status: ");
		builder.append(getStatus().getName());
		builder.append(", Payment Status: ");
		builder.append(PaymentStatus.toEnum(getPayment().getPaymentStatus().getCod()).getName());
		builder.append("\nDetail:\n");
		for (OrderItem oi : getItems()) {
			builder.append(oi.toString());
		}
		builder.append("Amount: ");
		builder.append(nf.format(getAmount()));
		return builder.toString();
	}
}

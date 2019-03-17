package com.invillia.acme.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.invillia.acme.domain.enumerator.PaymentStatus;

@Entity
@Table(name="payment")
public class Payment implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="order_id")
	private Order order;
	
	private Integer paymentStatus;
	
	private Integer credcardNumber;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date paymentDate;
	
	public Payment() {
		
	}

	public Payment(Order order, PaymentStatus paymentStatus, Integer credcardNumber, Date paymentDate) {
		super();
		this.order = order;
		this.paymentStatus = (paymentStatus == null) ? null : paymentStatus.getCod();
		this.credcardNumber = credcardNumber;
		this.paymentDate = paymentDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public PaymentStatus getPaymentStatus() {
		return PaymentStatus.toEnum(paymentStatus);
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus.getCod();
	}

	public Integer getCredcardNumber() {
		return credcardNumber;
	}

	public void setCredcardNumber(Integer credcardNumber) {
		this.credcardNumber = credcardNumber;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}

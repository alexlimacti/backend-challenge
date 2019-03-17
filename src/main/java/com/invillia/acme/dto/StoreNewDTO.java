package com.invillia.acme.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.invillia.acme.service.validation.StoreInsert;

@StoreInsert
public class StoreNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Required field")
	private String name;
	
	@NotEmpty(message = "Required field")
	private String street;
	
	@NotEmpty(message = "Required field")
	private String state;
	
	@NotEmpty(message = "Required field")
	private String district;
	
	@NotEmpty(message = "Required field")
	private String country;
	
	@NotEmpty(message = "Required field")
	private String city;
	
	@NotEmpty(message = "Required field")
	private String number;
	
	public StoreNewDTO() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}

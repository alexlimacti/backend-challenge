package com.invillia.acme.domain.enumerator;

public enum PaymentStatus {
	
	waiting(1, "Waiting Confirmation"),
	confirmed(2, "Confirmed"),
	refunded(3,"Refunded"),
	;
	
	private String name;
	private Integer cod;

	public String getName() {
		return name;
	}
	
	public Integer getCod() {
		return cod;
	}
	
	private PaymentStatus(Integer cod,String name) {
		this.cod = cod;
		this.name = name;
	}

	public String toString() {
		return this.name;
	}
	
	public static PaymentStatus toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(PaymentStatus x: PaymentStatus.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}

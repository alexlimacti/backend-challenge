package com.invillia.acme.domain.enumerator;

public enum OrderStatus {
	awaiting_payment(1, "Awaiting payment"),
	waiting(2, "Waiting to be sent"),
	confirmed(3, "Confirmed"),
	sent(4, "Sent"),
	delivered(5, "Delivered"),
	refunded(6,"Refunded"),
	;
	
	private String name;
	private Integer cod;
	
	private OrderStatus(Integer cod, String name) {
		this.name = name;
		this.cod = cod;
	}
	
	public String getName() {
		return name;
	}	
	
	public Integer getCod() {
		return cod;
	}

	public String toString() {
		return this.name;
	}
	
	public static OrderStatus toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(OrderStatus x: OrderStatus.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}

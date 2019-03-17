package com.invillia.acme;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.invillia.acme.domain.Address;
import com.invillia.acme.domain.Order;
import com.invillia.acme.domain.OrderItem;
import com.invillia.acme.domain.Payment;
import com.invillia.acme.domain.Store;
import com.invillia.acme.domain.enumerator.OrderStatus;
import com.invillia.acme.domain.enumerator.PaymentStatus;
import com.invillia.acme.repository.AddressRepository;
import com.invillia.acme.repository.OrderItemRepository;
import com.invillia.acme.repository.OrderRepository;
import com.invillia.acme.repository.PaymentRepository;
import com.invillia.acme.repository.StoreRepository;

@SpringBootApplication
public class InvilliaApplication implements CommandLineRunner {
	
	@Autowired
	private StoreRepository storeRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private OrderRepository orderService;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private OrderItemRepository oiRepository;

	public static void main(String[] args) {
		SpringApplication.run(InvilliaApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		Address a1 = new Address("Rua São Jorge","Bahia","Paulo Afonso", "Centro", "Brazil","1A");
		Address a2 = new Address("Rua São Mateus","Pernambuco","Jatobá", "Itaparica", "Brazil","258");
		Address a3 = new Address("Rua São Paulo","Sergipe","Petrolândia", "Agrovila", "Brazil","368C");
		Address a4 = new Address("Rua São Pedro","Ceará","Fortaleza", "Prainha", "Brazil","S/N");
		
		addressRepository.saveAll(Arrays.asList(a1,a2,a3,a4));
		
		Store s1 = new Store("Info", a2);
		Store s2 = new Store("Microsoft", a4);
		Store s3 = new Store("Google", a1);
		Store s4 = new Store("Apple", a3);
		
		storeRepository.saveAll(Arrays.asList(s1, s2, s3, s4));
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Order o1 = new Order(a1, sdf.parse("01/03/2019"),OrderStatus.confirmed);
		Order o2 = new Order(a1, sdf.parse("07/03/2019"),OrderStatus.awaiting_payment);
		
		orderService.saveAll(Arrays.asList(o1,o2));
		
		Payment p1 = new Payment(o1, PaymentStatus.waiting,123456789, null);
		Payment p2 = new Payment(o2, PaymentStatus.confirmed,123456789, sdf.parse("10/03/2019"));
		
		paymentRepository.saveAll(Arrays.asList(p1, p2));
		
		OrderItem oi1 = new OrderItem("Notebook", 1008.95,1);
		OrderItem oi2 = new OrderItem("Carro", 50892.74,1);
		OrderItem oi3 = new OrderItem("Roteador", 300.0,3);
		OrderItem oi4 = new OrderItem("Switch", 785.2,3);
		OrderItem oi5 = new OrderItem("servidor Dell", 5248.88,1);
		
		oiRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4, oi5));
		
		o1.getItems().addAll(Arrays.asList(oi1, oi2));
		o2.getItems().addAll(Arrays.asList(oi3, oi4, oi5));
		
		
		o1.setPayment(p1);
		o2.setPayment(p2);
		
		
		orderService.saveAll(Arrays.asList(o1, o2));
	}
}

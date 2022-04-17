package br.com.yvestaba.myecommerce.inbound.facade;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.yvestaba.myecommerce.business.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	private final OrderService orderService;
	
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PatchMapping("/{payment-id}/package")
	public ResponseEntity<Void> sendToPackage(@PathVariable("payment-id") String paymentId){
		orderService.sendToPackage(paymentId);
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping("/{payment-id}/transport")
	public ResponseEntity<Void> sendToCarriage(@PathVariable("payment-id") String paymentId){
		orderService.sendToCarriage(paymentId);
		return ResponseEntity.noContent().build();
	}
	
}

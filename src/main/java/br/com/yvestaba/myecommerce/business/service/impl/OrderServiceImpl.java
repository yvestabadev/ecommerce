package br.com.yvestaba.myecommerce.business.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.yvestaba.myecommerce.business.exceptions.NotFound404Exception;
import br.com.yvestaba.myecommerce.business.exceptions.UnprocessableEntityException;
import br.com.yvestaba.myecommerce.business.service.KafkaProducerService;
import br.com.yvestaba.myecommerce.business.service.OrderService;
import br.com.yvestaba.myecommerce.domain.Email;
import br.com.yvestaba.myecommerce.domain.OrderStatus;
import br.com.yvestaba.myecommerce.domain.constants.Topics;
import br.com.yvestaba.myecommerce.outbound.datasource.repositories.OrderRepository;
import br.com.yvestaba.myecommerce.outbound.jpa.OrderJpa;

@Service
public class OrderServiceImpl implements OrderService{
	
	private final OrderRepository orderRepository;
	private final KafkaProducerService producerService;
	
	public OrderServiceImpl(OrderRepository orderRepository, KafkaProducerService producerService) {
		this.orderRepository = orderRepository;
		this.producerService = producerService;
	}

	@Override
	public void sendToPackage(String paymentId) {
		OrderJpa order = this.getOrder(paymentId);		
		if(!order.getStatus().equals(OrderStatus.VALIDATING)) {
			throw new UnprocessableEntityException("This order cannot be sent to package");
		}
		
		orderRepository.changeOrderStatus(OrderStatus.PACKAGING, paymentId);
		producerService.produceEmail(this.getEmailByOrder(order, OrderStatus.PACKAGING), Topics.SEND_EMAIL);
	}

	@Override
	public void sendToCarriage(String paymentId) {
		OrderJpa order = this.getOrder(paymentId);
		if(!order.getStatus().equals(OrderStatus.PACKAGING)) {
			throw new UnprocessableEntityException("This order cannot be sent to carriage");
		}
		
		orderRepository.changeOrderStatus(OrderStatus.TRANSPORTING, paymentId);
		producerService.produceEmail(this.getEmailByOrder(order, OrderStatus.TRANSPORTING), Topics.SEND_EMAIL);
	}
	
	private Email getEmailByOrder(OrderJpa order, OrderStatus status) {
		Email email = new Email();
		email.setFrom("${spring.mail.username}");
		email.setTo(order.getUserEmail());
		email.setSubject("Your order was sent to " + status.toString().toLowerCase() + " department");
		email.setText("Right now, we are " + status.toString().toLowerCase() + " your order. For any doubts, please call +55 11 99999 8888");
		return email;
	}

	private OrderJpa getOrder(String paymentId) {
		Optional<OrderJpa> optionalOrder = orderRepository.findById(paymentId);
		if(!optionalOrder.isPresent()) {
			throw new NotFound404Exception("Order not found");
		}
		
		OrderJpa order = optionalOrder.get();
		return order;
	}

}

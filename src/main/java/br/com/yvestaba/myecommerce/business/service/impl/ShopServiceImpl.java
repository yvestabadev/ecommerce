package br.com.yvestaba.myecommerce.business.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.yvestaba.myecommerce.business.dto.OrderDTO;
import br.com.yvestaba.myecommerce.business.service.KafkaProducerService;
import br.com.yvestaba.myecommerce.business.service.ShopService;
import br.com.yvestaba.myecommerce.domain.Email;
import br.com.yvestaba.myecommerce.domain.EmailStatus;
import br.com.yvestaba.myecommerce.domain.OrderStatus;
import br.com.yvestaba.myecommerce.domain.constants.Topics;
import br.com.yvestaba.myecommerce.outbound.datasource.repositories.OrderRepository;
import br.com.yvestaba.myecommerce.outbound.jpa.OrderJpa;

@Service
public class ShopServiceImpl implements ShopService{

	private final KafkaProducerService kafkaProducer;
	private final ModelMapper modelMapper;
	private final OrderRepository orderRepository;
	
	public ShopServiceImpl(KafkaProducerService kafkaProducer, ModelMapper modelMapper,
			OrderRepository orderRepository) {
		this.kafkaProducer = kafkaProducer;
		this.modelMapper = modelMapper;
		this.orderRepository = orderRepository;
	}
	
	@Override
	public void postOrder(OrderDTO order) {	
		OrderJpa orderJpa = this.modelMapper.map(order, OrderJpa.class);
		orderJpa.setStatus(OrderStatus.VALIDATING);
		orderRepository.save(orderJpa);
		kafkaProducer.produceEmail(this.buildEmailByOrder(order), Topics.SEND_EMAIL);
		
	}

	private Email buildEmailByOrder(OrderDTO order) {
		Email email = new Email();
		email.setFrom("${spring.mail.username}");
		email.setTo(order.getUserId());
		email.setSubject("Your order is being processed");
		email.setStatus(EmailStatus.SENDING);
		email.setText("Hello, " + order.getUserName() + 
				"!\r\nThanks for your purchase. Your order is beign processed and you'll be notified about its progress.");
		return email;
		
	}

}

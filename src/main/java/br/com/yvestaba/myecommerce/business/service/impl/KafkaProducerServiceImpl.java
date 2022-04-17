package br.com.yvestaba.myecommerce.business.service.impl;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.yvestaba.myecommerce.business.service.KafkaProducerService;
import br.com.yvestaba.myecommerce.domain.Email;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService{
	
	private final KafkaTemplate<String, Email> kafkaTemplate;
	
	public KafkaProducerServiceImpl(KafkaTemplate<String, Email> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	@Override
	public void produceEmail(Email email, String topic) {
		kafkaTemplate.send(topic, email);
	}

}

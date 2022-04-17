package br.com.yvestaba.myecommerce.business.service;

import br.com.yvestaba.myecommerce.domain.Email;

public interface KafkaProducerService {

	void produceEmail(Email email, String topic);
	
}

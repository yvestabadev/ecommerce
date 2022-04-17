package br.com.yvestaba.myecommerce.business.service.impl;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import br.com.yvestaba.myecommerce.business.service.EmailService;
import br.com.yvestaba.myecommerce.config.KafkaConfiguration;
import br.com.yvestaba.myecommerce.domain.Email;
import br.com.yvestaba.myecommerce.domain.constants.Topics;

@Component
public class EmailConsumer {

	private final EmailService emailService;

	public EmailConsumer(EmailService emailService) {
		this.emailService = emailService;
	}

	@KafkaListener(topics = Topics.SEND_EMAIL, groupId = KafkaConfiguration.GROUP_ID_EMAIL, containerFactory = "emailFactory")
	public void consumeEmail(ConsumerRecord<String, Email> consumerRecord) {
		try {
			emailService.sendEmail(consumerRecord.value());
		} catch (Exception e) {

		}
	}

}

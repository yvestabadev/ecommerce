package br.com.yvestaba.myecommerce.config;

import static org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import br.com.yvestaba.myecommerce.domain.Email;

@Configuration
public class KafkaConfiguration {
	
	public static final String GROUP_ID_EMAIL = "EMAIL";
	
	@Bean
	public KafkaTemplate<String, Email> kafkaTemplate(){
		return new KafkaTemplate<String, Email>(emailProducerFactory());
	}
	
	@Bean
	public ProducerFactory<String, Email> emailProducerFactory(){
		return new DefaultKafkaProducerFactory<String, Email>(this.propertiesProducer(), new StringSerializer(), new JsonSerializer<Email>());
	}

	@Bean
	public ConsumerFactory<String, Email> emailConsumerFactory(){
		return new DefaultKafkaConsumerFactory<String, Email>(this.properties(), new StringDeserializer(), new JsonDeserializer<Email>());
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Email> emailFactory(){
		var factory = new ConcurrentKafkaListenerContainerFactory<String, Email>();
		factory.setConsumerFactory(emailConsumerFactory());
		return factory;
	}
	
	private Map<String, Object> properties() {
		var properties = new HashMap<String, Object>();
		properties.put(BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		properties.put(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		properties.put(VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		properties.put(GROUP_ID_CONFIG, GROUP_ID_EMAIL);
		properties.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		
		return properties;
	}
	
	private Map<String, Object> propertiesProducer() {
		var properties = new HashMap<String, Object>();
		properties.put(BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		properties.put(KEY_SERIALIZER_CLASS_CONFIG , StringDeserializer.class);
		properties.put(VALUE_SERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		
		return properties;
	}

}

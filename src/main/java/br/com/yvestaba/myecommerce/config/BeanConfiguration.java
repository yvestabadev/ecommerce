package br.com.yvestaba.myecommerce.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.yvestaba.myecommerce.business.dto.OrderDTO;
import br.com.yvestaba.myecommerce.outbound.jpa.OrderJpa;

@Configuration
public class BeanConfiguration {
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		mapper.createTypeMap(OrderDTO.class, OrderJpa.class).addMapping(OrderDTO::getUserId, OrderJpa::setUserEmail);
		return mapper;
	}

}

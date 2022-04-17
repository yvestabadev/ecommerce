package br.com.yvestaba.myecommerce.outbound.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import br.com.yvestaba.myecommerce.business.dto.OrderDTO;
import br.com.yvestaba.myecommerce.config.BeanConfiguration;
import br.com.yvestaba.myecommerce.domain.Adress;

class OrderJpaTest {
	
	ModelMapper modelMapper = new BeanConfiguration().modelMapper();

	@Test
	void autowiredModelMapperMustMapCorrectlyOrderDtoToOrderJpaEntity() {
		OrderDTO dto = new OrderDTO();
		Adress adress = new Adress();
		adress.setAdditionalInfo("addinfo");
		adress.setCity("São Paulo");
		adress.setNumber("688B");
		adress.setState("São Paulo");
		adress.setZipcode("02988020");
		dto.setAdress(adress);
		dto.setPaymentId("paymentId");
		dto.setProductId(1l);
		dto.setUserId("yvestaba@hotmail.com");
		dto.setUserName("Yves");
				
		OrderJpa order = modelMapper.map(dto, OrderJpa.class);
		
		assertEquals(dto.getAdress().getAdditionalInfo(), order.getAdress().getAdditionalInfo());
		assertEquals(dto.getAdress().getCity(), order.getAdress().getCity());
		assertEquals(dto.getAdress().getNumber(), order.getAdress().getNumber());
		assertEquals(dto.getAdress().getState(), order.getAdress().getState());
		assertEquals(dto.getAdress().getZipcode(), order.getAdress().getZipcode());
		assertEquals(dto.getCouponId(), order.getCouponId());
		assertEquals(dto.getPaymentId(), order.getPaymentId());
		assertEquals(dto.getProductId(), order.getProductId());
		assertEquals(dto.getUserName(), order.getUserName());
		assertEquals(dto.getUserId(), order.getUserEmail());
		assertNull(order.getStatus());
	}

}

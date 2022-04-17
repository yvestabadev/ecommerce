package br.com.yvestaba.myecommerce.business.dto;

import br.com.yvestaba.myecommerce.domain.Adress;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderDTO {
	
	private String userId;
	private String userName;
	private long productId;
	private String couponId;
	private String paymentId;
	private Adress adress;

}

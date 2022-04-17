package br.com.yvestaba.myecommerce.outbound.jpa;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.yvestaba.myecommerce.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="orders")
public class OrderJpa {
	
	@Id
	private String paymentId;
	private String userEmail;
	private String userName;
	private String couponId;
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	private Long productId;
	@Embedded
	private EmbeddedAdress adress;

}

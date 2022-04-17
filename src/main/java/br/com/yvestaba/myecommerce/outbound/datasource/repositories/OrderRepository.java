package br.com.yvestaba.myecommerce.outbound.datasource.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.yvestaba.myecommerce.domain.OrderStatus;
import br.com.yvestaba.myecommerce.outbound.jpa.OrderJpa;

@Repository
public interface OrderRepository extends JpaRepository<OrderJpa, String>{
	
	@Modifying
	@Transactional
	@Query("UPDATE OrderJpa o SET o.status = :status WHERE o.paymentId = :id")
	void changeOrderStatus(OrderStatus status, String id);
}

package br.com.yvestaba.myecommerce.business.service;

public interface OrderService {

	void sendToPackage(String paymentId);
	void sendToCarriage(String paymentId);
	
}

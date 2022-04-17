package br.com.yvestaba.myecommerce.inbound.facade;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.yvestaba.myecommerce.business.dto.OrderDTO;
import br.com.yvestaba.myecommerce.business.service.ShopService;

@RestController
@RequestMapping("/shop")
public class ShopController {

	private final ShopService shopService;
	
	public ShopController(ShopService shopService) {
		this.shopService = shopService;
	}
	
	@PostMapping
	public ResponseEntity<String> buy(@RequestBody OrderDTO order, HttpServletRequest request){
		request.setAttribute("email", order.getUserId());
		request.setAttribute("orderId", order.getPaymentId());
		shopService.postOrder(order);
		return ResponseEntity.created(URI.create("/orders")).body("Your order is beign processed!");
	}
}

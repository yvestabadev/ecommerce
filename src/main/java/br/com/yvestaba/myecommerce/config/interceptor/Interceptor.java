package br.com.yvestaba.myecommerce.config.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import br.com.yvestaba.myecommerce.business.service.KafkaProducerService;
import br.com.yvestaba.myecommerce.domain.Email;
import br.com.yvestaba.myecommerce.domain.EmailStatus;
import br.com.yvestaba.myecommerce.domain.constants.Topics;
import br.com.yvestaba.myecommerce.outbound.datasource.repositories.UserRepository;
import br.com.yvestaba.myecommerce.outbound.jpa.UserJpa;
import io.jsonwebtoken.Jwts;

@Component
public class Interceptor implements HandlerInterceptor{
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private KafkaProducerService producerService;
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		String emailRequest = (String) request.getAttribute("email");
		String token = request.getHeader("Authorization");
		token = token.substring(7, token.length());
		String userId = Jwts.parser().setSigningKey(this.secret)
				.parseClaimsJws(token).getBody().getSubject();
		UserJpa user = userRepository.findById(userId).get();
		
		if(!emailRequest.equals(user.getEmail())) {
			producerService.produceEmail(this.getEmailWarnFraud(request.getAttribute("orderId")), Topics.SEND_EMAIL);
		}
		
	}

	private Email getEmailWarnFraud(Object orderId) {
		String order = (String) orderId;
		Email email = new Email();
		email.setFrom("yvestaba.dev@gmail.com");
		email.setTo("yvestaba@hotmail.com");
		email.setSubject("Fraud Warning");
		email.setStatus(EmailStatus.SENDING);
		email.setText("Possible fraud on order number " + order + 
				". Please make sure if it's a valid request, since the user id is different from the jwt token");
		return email;
	}

}

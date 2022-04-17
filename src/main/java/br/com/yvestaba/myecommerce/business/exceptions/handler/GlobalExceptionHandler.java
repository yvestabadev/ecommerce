package br.com.yvestaba.myecommerce.business.exceptions.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.yvestaba.myecommerce.business.exceptions.NotFound404Exception;
import br.com.yvestaba.myecommerce.business.exceptions.UnprocessableEntityException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NotFound404Exception.class)
	public ResponseEntity<String> handling(NotFound404Exception e){
		return ResponseEntity.status(404).body(e.getMessage());
	}
	
	@ExceptionHandler(UnprocessableEntityException.class)
	public ResponseEntity<String> handling(UnprocessableEntityException e){
		return ResponseEntity.status(422).body(e.getMessage());
	}
	
}

package br.com.yvestaba.myecommerce.business.exceptions;

public class NotFound404Exception extends RuntimeException {

	private static final long serialVersionUID = -7564094039610850694L;

	public NotFound404Exception(String message) {
		super(message);
	}

}

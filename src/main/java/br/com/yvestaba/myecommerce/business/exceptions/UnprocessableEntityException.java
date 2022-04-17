package br.com.yvestaba.myecommerce.business.exceptions;

public class UnprocessableEntityException extends RuntimeException {

	private static final long serialVersionUID = -4617812174221082348L;

	public UnprocessableEntityException(String message) {
		super(message);
	}

}

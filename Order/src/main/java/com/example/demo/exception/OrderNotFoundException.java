package com.example.demo.exception;

public class OrderNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7010891170523976904L;

	public OrderNotFoundException(String message) {
		super(message);
	}
}

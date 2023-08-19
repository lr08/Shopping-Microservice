package com.example.demo.exception;

public class CustomerNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8063025300734221597L;

	public CustomerNotFoundException(String message) {
		super(message);
	}
}

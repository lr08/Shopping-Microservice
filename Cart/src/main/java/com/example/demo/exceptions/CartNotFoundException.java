package com.example.demo.exceptions;

public class CartNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7010891170523976904L;

	public CartNotFoundException(String message) {
		super(message);
	}
}

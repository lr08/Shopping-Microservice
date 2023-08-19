package com.example.demo.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7817067816597651980L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
}

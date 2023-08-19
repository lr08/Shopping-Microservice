package com.example.demo.exceptions;

public class InventoryNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 46348836240467991L;

	public InventoryNotFoundException(String message) {
		super(message);
	}
}

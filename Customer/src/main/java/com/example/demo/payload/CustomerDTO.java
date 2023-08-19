package com.example.demo.payload;

import com.example.demo.entity.Address;

import lombok.Data;

@Data
public class CustomerDTO {

	private int customerId;
	private String customerEmail;
	private String customerName;
	private Address customerBillingAddress;
	private Address shippingAddress;
}

package com.example.demo.modal;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	private int customerId;
	
	private String customerName;
	
	private String customerEmail;
	
	@Embedded
	@Column(name="customer_BillingAddress")
	@AttributeOverrides({
		@AttributeOverride(name="doorNo",column=@Column(name="billing_doorNo")),
		@AttributeOverride(name="street",column=@Column(name="billing_street")),
		@AttributeOverride(name="layout",column=@Column(name="billing_layout")),
		@AttributeOverride(name="city",column=@Column(name="billing_city")),
		@AttributeOverride(name="pincode",column=@Column(name="billing_pincode")),
		
	})
	private Address customerBillingAddress;
	
	@Embedded
	@Column(name="Shipping_Address")
	@AttributeOverrides({
		@AttributeOverride(name="doorNo",column=@Column(name="shipping_doorNo")),
		@AttributeOverride(name="street",column=@Column(name="shipping_street")),
		@AttributeOverride(name="layout",column=@Column(name="shipping_layout")),
		@AttributeOverride(name="city",column=@Column(name="shipping_city")),
		@AttributeOverride(name="pincode",column=@Column(name="shipping_pincode")),
	})
	private Address shippingAddress;
}

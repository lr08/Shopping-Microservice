package com.example.demo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

	private int productId;
	private String productName;
	private String productDescription;
	private double productPrice;
	private int productQuantity;
}

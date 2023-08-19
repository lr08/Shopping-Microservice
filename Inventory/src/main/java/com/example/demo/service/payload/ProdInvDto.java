package com.example.demo.service.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdInvDto {

	int inventoryId;
	int productId;
	int quantity;
	int cartId;
	
	private String productName;
	private double productPrice;
}

package com.example.demo.modal;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
	
	private int inventoryId;
	private int productId;
	private int quantity;

}

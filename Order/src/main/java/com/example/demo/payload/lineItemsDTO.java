package com.example.demo.payload;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class lineItemsDTO {

	@Id
	private String itemId;
	private int inventoryId;
	private int quantity;
}

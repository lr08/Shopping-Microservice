package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Inventory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int inventoryId;
	private int productId;
	private int quantity;
	
	public Inventory(int productId, int quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}

	public Inventory(int inventoryId, int productId, int quantity) {
		super();
		this.inventoryId = inventoryId;
		this.productId = productId;
		this.quantity = quantity;
	}

}

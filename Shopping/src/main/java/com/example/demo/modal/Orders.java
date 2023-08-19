package com.example.demo.modal;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
	
	private String orderId;
	private int customerId;
	private List<LineItems> items;

}

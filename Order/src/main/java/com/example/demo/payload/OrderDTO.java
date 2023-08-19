package com.example.demo.payload;

import java.util.List;

import org.springframework.data.annotation.Id;

import com.example.demo.entity.LineItems;

import lombok.Data;

@Data
public class OrderDTO {

	@Id
	private String orderId;
	private int customerId;
	private List<LineItems> items;
}

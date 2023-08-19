package com.example.demo.payload;

import java.util.List;

import com.example.demo.entity.LineItems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {

	private int cartId;	
	private List<LineItems> items;
}

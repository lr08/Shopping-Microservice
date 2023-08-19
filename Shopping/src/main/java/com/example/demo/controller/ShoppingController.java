package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modal.Customer;
import com.example.demo.modal.InformationDto;
import com.example.demo.modal.LineItems;
import com.example.demo.modal.Orders;
import com.example.demo.modal.Product;
import com.example.demo.service.ShoppingService;

@RestController
@RequestMapping("/shoppingservice")
public class ShoppingController {
	
	@Autowired
	private ShoppingService service;

	@PostMapping("/product")
	public String addProduct(@RequestBody Product product) {
		service.addProduct(product);
		return "Product added";
	}
	
	@PostMapping("/customer")
	public Customer addCustomer(@RequestBody Customer customer) {
		return service.addCustomer(customer);
	}
	
	@PutMapping("/customer/{customerId}")
	public String addCart(@PathVariable int customerId,@RequestBody List<LineItems> litems) {
		service.addCart(customerId, litems);
		return "cart added";
	}
	
	@PostMapping("/customer/{customerId}/order")
	public Orders addOrder(@PathVariable int customerId) {
		return service.addOrder(customerId);
	}
	
	@GetMapping("/customer/{customerId}/orders")
	public InformationDto retrieveOrders(@PathVariable("customerId") int customerId) {
		return service.retrieveOrders(customerId);
	}
}

package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Orders;
import com.example.demo.payload.OrderDTO;
import com.example.demo.service.OrderService;

@RestController

@RequestMapping("/orders")
public class OrderController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

	public static Logger getLogger(){ 
		return LOGGER;
	}

	@Autowired
	private OrderService service;

	@PostMapping
	public ResponseEntity<OrderDTO> addOrder(@RequestBody OrderDTO orderDto) {
		return new ResponseEntity<>(service.addOrder(orderDto), HttpStatus.CREATED);
	}

	@PutMapping("/items/{orderId}")
	public ResponseEntity<OrderDTO> addItem(@PathVariable String orderId, @RequestBody OrderDTO orderDto){ 
		return new ResponseEntity<>(service.addItems (orderId, orderDto), HttpStatus.OK);
	}

	@PutMapping("/{orderId}")
	public ResponseEntity<OrderDTO> updateOrder(@PathVariable String orderId, @RequestBody OrderDTO orderDto){
		return new ResponseEntity<>(service.updateOrder(orderId, orderDto), HttpStatus.OK);
	}
	
	
	@GetMapping("/all")
	public List OrdersgetAllOrders(){
		return service.getAll();
	}
		
	@GetMapping("/{orderId}")
	public Orders getOrders(@PathVariable String orderId) { 
		return service.getOrders(orderId);
	}
	
	@DeleteMapping("/{orderId}")
	public ResponseEntity<String> deleteorder(@PathVariable String orderId) {
		service.deleteOrder(orderId);
		return new ResponseEntity<>("order canceled", HttpStatus.OK);
	}
	

//	@DeleteMapping("/items/(itemId)")
//	public ResponseEntity<String> deleteltens (@PathVariable String itenId) {
//		service.deleteLineItems(itemId); return new ResponseEntity("item canceled", HttpStatus.OK);
//	}
	
	@DeleteMapping("/all")
	public String deleteAllOrders() { 
		service.deleteOrders(); 
		return "All orders deleted";
	}
}	
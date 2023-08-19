package com.example.demo.controller;

import javax.sound.sampled.Line;

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

import com.example.demo.payload.LineItemsDTO;
import com.example.demo.service.CartService;

@RestController
@RequestMapping("/items")
public class ItemsController {
	
	@Autowired
	private CartService service;
	
	@PostMapping("/{cartId}")
	public ResponseEntity<LineItemsDTO> addLineItems (@PathVariable Integer cartId, @RequestBody LineItemsDTO lineItemsDto) {
		return new ResponseEntity<>(service.saveLineItems(cartId, lineItemsDto), HttpStatus.OK);
		}
	
	@PutMapping("/{itemId}")
	public LineItemsDTO updateLineItems(@PathVariable Integer itemId, @RequestBody LineItemsDTO lineItemsDto){
		return service.updateLineItems(itemId, lineItemsDto);
	}
	
	@GetMapping("/{itemId}")
	public LineItemsDTO getLineItemsById(@PathVariable Integer itemId) { return service.searchLineItems (itemId);
	
	}
	
	@DeleteMapping("/{itemId}")
	public void deleteLineItemsById(@PathVariable Integer itemId) { 
		service.deletelineItems (itemId);
	}
}	

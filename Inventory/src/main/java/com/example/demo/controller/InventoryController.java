package com.example.demo.controller;

import java.util.List;

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

import com.example.demo.service.InventoryService;
import com.example.demo.service.payload.InventoryDTO;
import com.example.demo.service.payload.ProdInvDto;

@RestController

@RequestMapping("/inventory")

public class InventoryController {

	@Autowired

	InventoryService service;

	@PostMapping

	public ResponseEntity<InventoryDTO> addInventory(@RequestBody InventoryDTO inventoryDto) {

		return new ResponseEntity<>(service.addInventory(inventoryDto), HttpStatus.CREATED);

	}

	@PutMapping("/{id}")

	public ResponseEntity<InventoryDTO> updateInventory(@RequestBody InventoryDTO inventoryDto, @PathVariable int id) {

		return new ResponseEntity<>(service.updateInventory(id, inventoryDto), HttpStatus.OK);

	}

	@GetMapping("/{id}")

	public ResponseEntity<InventoryDTO> searchInventory(@PathVariable int id) {

		return new ResponseEntity<>(service.searchInventory(id), HttpStatus.OK);

	}

	@GetMapping

	public List<InventoryDTO> searchAllInventory() {

		return service.searchAllInventory();

	}

	@DeleteMapping("/{id}")

	public ResponseEntity<String> deleteInventory(@PathVariable int id) {

		service.deleteInventory(id);

		return new ResponseEntity<>("Deleted", HttpStatus.OK);

	}

	@PutMapping("/{id}/quantity/{qty}")

	public String decreaseInventory(@PathVariable int id, @PathVariable int qty) {

		service.decreaseInventory(id, qty);

		return "Quantity decreased";

	}

	@GetMapping("/invwithpro/{id}/quantity/{qty}")

	public ProdInvDto inventoryWithProduct(@PathVariable int id, @PathVariable int qty) {

		return service.getInvWithProduct(id, qty);

	}

}

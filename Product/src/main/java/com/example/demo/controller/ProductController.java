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

import com.example.demo.entity.Product;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.payload.ProductDTO;
import com.example.demo.service.ProductService;

import lombok.AllArgsConstructor;

@RestController

@AllArgsConstructor

@RequestMapping("/products")

public class ProductController {

	@Autowired

	private ProductService service;

	@PostMapping("/publish")

	public ResponseEntity<String> publishProduct(@RequestBody ProductDTO productDto) {

		service.sendProduct(productDto);

		return ResponseEntity.ok("Product id and quantity is been published!!");

	}

	@PostMapping

	public ResponseEntity<Product> addProduct(@RequestBody ProductDTO productDto) {

		Product product = service.addProduct(productDto);

		return new ResponseEntity<>(product, HttpStatus.CREATED);

	}

	@GetMapping("/{id}")

	public Product searchProduct(@PathVariable("id") int productId) {

		Product foundProduct = service.findProductById(productId);

		if (foundProduct == null)

			throw new ResourceNotFoundException("Product details not found");

		return foundProduct;

	}

	@PutMapping("/{id}")

	public ResponseEntity<Product> updateProduct(@RequestBody ProductDTO productDto, @PathVariable int id){

		return new ResponseEntity<>(service.updateProduct(id, productDto), HttpStatus.OK);

	}

	@DeleteMapping("/{id}")

	public ResponseEntity<String> deleteProduct(@PathVariable int id) {

		service.deleteProduct(id);

		return new ResponseEntity<>("Product data deleted successfully", HttpStatus.OK);

	}

	@GetMapping("/all")

	public List<Product> searchAllProducts() {

		return service.findAllProducts();

	}

	@DeleteMapping

	public ResponseEntity<String> deleteAllProducts() {

		service.deleteAllProducts();

		return ResponseEntity.ok("Deleted all products");

	}

}

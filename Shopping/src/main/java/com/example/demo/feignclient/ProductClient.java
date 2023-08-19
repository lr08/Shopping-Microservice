package com.example.demo.feignclient;

import java.util.List;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.modal.Product;

@FeignClient(name = "PRODUCT-SERVICE")
@LoadBalancerClient(name="PRODUCT-SERVICE")
public interface ProductClient {

	@PostMapping("/products/publish")
	public String publishProduct(@RequestBody Product product);

	@PostMapping("/products")
	public Product addProduct(@RequestBody Product product);

	@GetMapping("/products/{id}")
	public Product searchProduct(@PathVariable("id") int productId);

	@PutMapping("/products/{id}")
	public Product updateProduct(@RequestBody Product product, @PathVariable int id);

	@DeleteMapping("/products/{id}")
	public String deleteProduct(@PathVariable int id);

	@GetMapping("/products/all")
	public List<Product> searchAllProducts();

	@DeleteMapping("/products")
	public String deleteAllProducts();

}
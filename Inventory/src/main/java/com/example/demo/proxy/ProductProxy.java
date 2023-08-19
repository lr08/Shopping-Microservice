package com.example.demo.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.modal.Product;

@FeignClient(name="PRODUCT-SERVICE")
public interface ProductProxy {
	
	@GetMapping("/products/{productId}")
	public Product searchProduct(@PathVariable int productId);

}

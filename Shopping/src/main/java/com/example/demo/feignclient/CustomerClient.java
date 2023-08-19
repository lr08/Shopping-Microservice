package com.example.demo.feignclient;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.modal.Customer;

@FeignClient(name="CUSTOMER-SERVICE")
@LoadBalancerClient(name="CUSTOMER-SERVICE")
public interface CustomerClient {

	@PostMapping("/customer")
	public Customer addCustomer(@RequestBody Customer customer);
	
	@GetMapping("/customer/{customerId}")
	public Customer searchCustomer(@PathVariable int customerId);
	
}

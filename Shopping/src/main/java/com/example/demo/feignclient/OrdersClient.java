package com.example.demo.feignclient;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.modal.Orders;

@FeignClient(name="ORDER-SERVICE")
@LoadBalancerClient(name="ORDER-SERVICE")
public interface OrdersClient {

	@PostMapping("/orders")
	public Orders addOrder(@RequestBody Orders order);
	
	@GetMapping("/orders/{orderId}")
	public Orders getOrders(@PathVariable String orderId);
	
	@PutMapping("/orders/{orderId}")
	public Orders updateOrder(@PathVariable String orderId,@RequestBody Orders order);
}

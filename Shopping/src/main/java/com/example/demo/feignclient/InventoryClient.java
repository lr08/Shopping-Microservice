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

import com.example.demo.modal.Inventory;
import com.example.demo.modal.ProdInvDto;

@FeignClient(name = "INVENTORY-SERVICE")
@LoadBalancerClient(name="INVENTORY-SERVICE")
public interface InventoryClient {

	@PostMapping("/inventory")
	public Inventory addInventory(@RequestBody Inventory inventory);

	@PutMapping("/inventory/{id}")
	public Inventory updateInventory(@RequestBody Inventory inventory, @PathVariable int id);

	@GetMapping("/inventory/{id}")
	public Inventory searchInventory(@PathVariable int id);

	@DeleteMapping("/inventory/{id}")
	public String deleteInventory(@PathVariable int id);

	@GetMapping("/inventory")
	public List<Inventory> searchAllInventory();

	@GetMapping("/inventory/invwithpro/{id}/quantity/{qty}")
	public ProdInvDto inventoryWithProduct(@PathVariable int id, @PathVariable int qty);

	@PutMapping("/inventory/{id}/quantity/{qty}")
	public void decreaseInventory(@PathVariable int id, @PathVariable int qty);
}
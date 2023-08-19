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

import com.example.demo.entity.Customer;
import com.example.demo.payload.CustomerDTO;
import com.example.demo.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService service;
	
	@PostMapping
	public ResponseEntity<CustomerDTO> addCustomer(@RequestBody CustomerDTO customerDto){
		return new ResponseEntity<>(service.addCustomer(customerDto),HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable int id, @RequestBody CustomerDTO customerDto){
		return new ResponseEntity<>(service.updateCustomer(id, customerDto),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Customer> searchCustomer(@PathVariable("id") int customerId){
		return new ResponseEntity<>(service.findCustomerById(customerId),HttpStatus.OK);
	}
	
	@GetMapping
	public List<CustomerDTO> getALlCustomers(){
		return service.findAllCustomers();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") int customerId){
		service.deleteCustomer(customerId);
		return new ResponseEntity<>("Customer data deleted successfully",HttpStatus.OK);
	}
	
}

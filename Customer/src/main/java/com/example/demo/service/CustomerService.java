package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Customer;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.payload.CustomerDTO;
import com.example.demo.repository.CustomerRepo;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepo repo;
	
	private ModelMapper mapper;

	public CustomerService(CustomerRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	private CustomerDTO mapToDTO(Customer customer) {
		CustomerDTO customerDto = mapper.map(customer,CustomerDTO.class);
		return customerDto;
	}
	
	private Customer mapToEntity(CustomerDTO customerDto) {
		Customer customer = mapper.map(customerDto, Customer.class);
		return customer;
	}
	
	public CustomerDTO addCustomer(CustomerDTO customerDto) {
		Customer customer = mapToEntity(customerDto);
		return mapToDTO(repo.save(customer));
	}
	
	public CustomerDTO updateCustomer(Integer customerId,CustomerDTO customerDto) {
		Customer customer = repo.findById(customerId).orElseThrow(()->new CustomerNotFoundException("Customer not found"));
		customer.setCustomerName(customerDto.getCustomerName());
		customer.setCustomerEmail(customerDto.getCustomerEmail());
		customer.setCustomerBillingAddress(customerDto.getCustomerBillingAddress());
		customer.setShippingAddress(customer.getShippingAddress());
		return mapToDTO(repo.save(customer));
	}
	
	public Customer findCustomerById(int customerId) {
		return repo.findById(customerId).orElseThrow(()->new CustomerNotFoundException("Customer not found"));
	}
	
	public List<CustomerDTO> findAllCustomers(){
		List<Customer> customers = repo.findAll();
		return customers.stream().map(customer->mapToDTO(customer)).collect(Collectors.toList());
	}
	
	public void deleteCustomer(int customerId) {
		repo.deleteById(customerId);
	}
}


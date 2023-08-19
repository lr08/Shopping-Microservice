package com.example.demo.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.LineItems;
import com.example.demo.entity.Orders;
import com.example.demo.exception.OrderNotFoundException;
import com.example.demo.payload.OrderDTO;
import com.example.demo.repository.OrderRepo;
import com.example.demo.repository.itemRepo;

@Service
public class OrderService{
	
	@Autowired
	private OrderRepo orepo;
	
	@Autowired
	private itemRepo irepo;
	
	private ModelMapper mapper;
	
	public OrderService(OrderRepo orepo, itemRepo irepo, ModelMapper mapper) {
		super();
		this.orepo = orepo;
		this.irepo = irepo;
		this.mapper = mapper;
	}

	private OrderDTO mapToDTO(Orders orders) {
		OrderDTO orderDto = mapper.map(orders,OrderDTO.class);
		return orderDto;
	}
	
	//POST
	public OrderDTO addOrder(OrderDTO orderDto) {
		Orders orders = new Orders();
		orders.setOrderId(orderDto.getOrderId());
		orders.setCustomerId(orderDto.getCustomerId());
		orders.setItems(orderDto.getItems());
		
		orders.getItems().forEach(items->irepo.save(items));
		Orders newOrder = orepo.save(orders);
		return mapToDTO(newOrder);
	}
	
	//PUT
	public OrderDTO addItems(String orderId,OrderDTO orderDto) {
		Orders order1=orepo.findById(orderId).orElseThrow(()->new OrderNotFoundException("Order not found"));
		List<LineItems> existingLineItems=order1.getItems();
		existingLineItems.addAll(orderDto.getItems());
		order1.getItems().stream().forEach(items->irepo.save(items));
		return mapToDTO(orepo.save(order1));
	}
	
	//PUT
	public OrderDTO updateOrder(String orderId,OrderDTO orderDto) {
		Orders order1 = orepo.findById(orderId).orElseThrow(()->new OrderNotFoundException("Order not found"));
		order1.setCustomerId(orderDto.getCustomerId());
		order1.setItems(orderDto.getItems());
		order1.getItems().stream().forEach(items->irepo.save(items));
		return mapToDTO(orepo.save(order1));
	}
	
	//GET
	public List<Orders> getAll(){
		return orepo.findAll();
	}
	
	//DELETE
	public void deleteOrder(String orderId) {
		Orders order1=orepo.findById(orderId).orElseThrow(()->new OrderNotFoundException("Order not found"));
		order1.getItems().stream().forEach(items->irepo.delete(items));
		orepo.deleteById(orderId);
	}
	
	public Orders getOrders(String orderId) {
		return orepo.findById(orderId).orElseThrow(()->new OrderNotFoundException("Order not found"));
	}
	
	public void deleteOrders() {
		irepo.deleteAll();
		orepo.deleteAll();
	}
}

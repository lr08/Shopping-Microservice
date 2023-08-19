package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.feignclient.CartClient;
import com.example.demo.feignclient.CustomerClient;
import com.example.demo.feignclient.InventoryClient;
import com.example.demo.feignclient.OrdersClient;
import com.example.demo.feignclient.ProductClient;
import com.example.demo.modal.Cart;
import com.example.demo.modal.Customer;
import com.example.demo.modal.CustomerCart;
import com.example.demo.modal.CustomerOrder;
import com.example.demo.modal.InformationDto;
import com.example.demo.modal.LineItems;
import com.example.demo.modal.Orders;
import com.example.demo.modal.ProdInvDto;
import com.example.demo.modal.Product;
import com.example.demo.repository.CustomerCartRepository;
import com.example.demo.repository.CustomerOrderRepository;

@Service
public class ShoppingService {
	
	@Autowired
	private CustomerClient customerClient;
	
	@Autowired
	private CartClient cartClient;
	
	@Autowired
	private ProductClient productClient;
	
	@Autowired
	private InventoryClient inventoryClient;
	
	@Autowired
	private OrdersClient orderClient;
	
	@Autowired
	private CustomerCartRepository ccRepo;
	
	@Autowired
	private CustomerOrderRepository coRepo;
	
	//1
	public void addProduct (@RequestBody Product product) {
		productClient.publishProduct(product);
	}
	
	//2
	public Customer addCustomer(Customer customer) {
		Customer addedCustomer = customerClient.addCustomer(customer);
		Cart cart = cartClient.createEmptyCart();
		CustomerCart cc = new CustomerCart(addedCustomer.getCustomerId(),cart.getCartId());
		ccRepo.save(cc);
		return addedCustomer;
	}
	
	//3
	public void addCart(Integer customerId,List<LineItems> litems) {
		CustomerCart cc = ccRepo.findById(customerId).orElseThrow(()-> new ResourceNotFoundException("Customer Not Found"));
		Integer cc1 = cc.getCartId();
		Cart cart = cartClient.searchCart(cc1);
		cart.setItems(litems);
		cartClient.updateCart(cc1,cart);
	}
	
	public Orders addOrder(Integer customerId) {
		CustomerCart cc = ccRepo.findById(customerId).orElseThrow(()-> new ResourceNotFoundException("Customer Not Found"));
		Cart cart = cartClient.searchCart(cc.getCartId());
		Orders order = new Orders();
		order.setOrderId(customerId.toString());
		order.setCustomerId(customerId);
		order.setItems(cart.getItems());
		cart.getItems().forEach(item->{inventoryClient.decreaseInventory(item.getInventoryId(),item.getQuantity());cartClient.deleteCartItems(item.getItemId());});
		Orders addedOrder = orderClient.addOrder(order);
		CustomerOrder cc0 = new CustomerOrder(customerId,addedOrder.getOrderId());
		coRepo.save(cc0);
		return addedOrder;
	}
	
	public InformationDto retrieveOrders(Integer customerId) {
		CustomerOrder cc0 = coRepo.findById(customerId).orElseThrow(()-> new ResourceNotFoundException("Customer Not Found"));
		Customer cust = customerClient.searchCustomer(cc0.getCustomerId());
		Orders order = orderClient.getOrders(cc0.getOrderId());
		InformationDto info = new InformationDto();
		info.setCustomerName(cust.getCustomerName());
		info.setCustomerEmail(cust.getCustomerEmail());
		info.setCustomerBillingAddress(cust.getCustomerBillingAddress());
		List<ProdInvDto> products = new ArrayList<>();
		order.getItems().forEach(item->products.add(inventoryClient.inventoryWithProduct(item.getInventoryId(),item.getQuantity())));
		info.setProdInfo(products);
		Double amount =0.0;
		for(ProdInvDto items:products) {
			amount = amount + items.getProductPrice()*items.getQuantity();
		}
		info.setTotalAmount(amount);
		return info;
	}
	
}

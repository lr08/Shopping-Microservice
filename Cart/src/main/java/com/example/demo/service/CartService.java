package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Cart;
import com.example.demo.entity.LineItems;
import com.example.demo.exceptions.CartNotFoundException;
import com.example.demo.payload.CartDTO;
import com.example.demo.payload.LineItemsDTO;
import com.example.demo.repository.CartRepo;
import com.example.demo.repository.LineItemsRepo;

@Service
public class CartService {

	@Autowired
	private CartRepo crepo;
	
	@Autowired
	private LineItemsRepo lrepo;
	
	private ModelMapper mapper;

	public CartService(CartRepo crepo, LineItemsRepo lrepo, ModelMapper mapper) {
		super();
		this.crepo = crepo;
		this.lrepo = lrepo;
		this.mapper = mapper;
	}
	
	private CartDTO mapToDTO(Cart cart) {
		CartDTO cartDto = mapper.map(cart, CartDTO.class);
		return cartDto;
	}
	
	private Cart mapToEntity(CartDTO cartDto) {
		Cart cart = mapper.map(cartDto, Cart.class);
		return cart;
	}
	
	//POST
	public CartDTO saveCart(CartDTO cartDto) {
		Cart cart =  mapToEntity(cartDto);
		Cart newCart =  crepo.save(cart);
		return mapToDTO(newCart);
	}
	
	public CartDTO searchCart(Integer cartId) {
		Cart cart = crepo.findById(cartId).orElseThrow(()-> new CartNotFoundException("Cart not found"));
		return mapToDTO(cart);
	}
	
	public Cart emptyCart() {
		Cart cart =  new Cart();
		crepo.save(cart);
		List<LineItems> items = new ArrayList<>();
		cart.setItems(items);
		crepo.save(cart);
		return cart;
	}
	
	public CartDTO updateCart(Integer cartId, CartDTO cartDto) {
		Cart cart = crepo.findById(cartId).orElseThrow(()-> new CartNotFoundException("Cart not found"));
		cart.setItems(cartDto.getItems());
		Cart updateCart = crepo.save(cart);
		return mapToDTO(updateCart);
	}
	
	public boolean isCartAvailable(int id) {
		return crepo.findById(id).isPresent();
	}
	
	public void deleteCart(Integer cartId) {
		crepo.deleteById(cartId);
	}
	
	public List<Cart> getAll(){
		return crepo.findAll();
	}
	
	private LineItemsDTO mapToDTO(LineItems lineItems) {
		LineItemsDTO lineItemsDto = mapper.map(lineItems, LineItemsDTO.class);
		return lineItemsDto;
	}
	
	private LineItems mapToEntity(LineItemsDTO lineItemsDTO) {
		LineItems lineItems = mapper.map(lineItemsDTO, LineItems.class);
		return lineItems;
	}
	
	public LineItemsDTO saveLineItems(int cartId,LineItemsDTO lineItemsDto) {
		Cart cart = crepo.findById(cartId).orElseThrow(()-> new CartNotFoundException("Cart not found"));
		if(cart.getCartId()!=0){
			LineItems lineItems = mapToEntity(lineItemsDto);
			cart.getItems().add(lineItems);
			LineItems newlineItems = lrepo.save(lineItems);
			return mapToDTO(newlineItems);
		}else {
			return null;
		}
	}
	
	public LineItemsDTO updateLineItems(Integer itemId,LineItemsDTO lineItemsDto) {
		LineItems items = lrepo.findById(itemId).orElseThrow(()-> new CartNotFoundException("Cart not found"));
		items.setInventoryId(lineItemsDto.getInventoryId());
		items.setQuantity(lineItemsDto.getQuantity());
		LineItems newlineItems = lrepo.save(items);
		return mapToDTO(newlineItems);
	}
	
	public LineItemsDTO searchLineItems(int itemId) {
		LineItems items = lrepo.findById(itemId).orElseThrow(()-> new CartNotFoundException("Cart not found"));
		return mapToDTO(items);
	}
	
	public void deletelineItems(int itemId) {
		lrepo.deleteById(itemId);
	}
	
	
}

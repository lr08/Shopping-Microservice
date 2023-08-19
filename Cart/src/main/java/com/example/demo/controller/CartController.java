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

import com.example.demo.entity.Cart;
import com.example.demo.payload.CartDTO;
import com.example.demo.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	public ResponseEntity<CartDTO> addCart (@RequestBody CartDTO cartDto){ 
		return new ResponseEntity<>(cartService.saveCart(cartDto), HttpStatus.CREATED);
	}



	@PutMapping("/{cartId}")
	public ResponseEntity<CartDTO> updateCart (@PathVariable Integer cartId, @RequestBody CartDTO cartDto){
		return new ResponseEntity<>(cartService.updateCart(cartId, cartDto), HttpStatus.OK);

	}

	@GetMapping("/all")
	public List<Cart> getAllCart(){ 
		return cartService.getAll();

	}

	@GetMapping("/{cartId}")
	public CartDTO getCart (@PathVariable int cartId) {
		return cartService.searchCart(cartId);

	}

	@DeleteMapping("{cartId}")
	public ResponseEntity<String> deleteCart (@PathVariable Integer cartId) {
		cartService.deleteCart (cartId);
		return new ResponseEntity<>("cart emptied", HttpStatus.OK);
	}


	@PostMapping("/emptyCart")
	public Cart createEmptyCart() {
		return cartService.emptyCart();
		}
}

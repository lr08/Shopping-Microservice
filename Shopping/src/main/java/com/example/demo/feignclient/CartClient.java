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

import com.example.demo.modal.Cart;
import com.example.demo.modal.LineItems;


@FeignClient(name="CART-SERVICE")
@LoadBalancerClient(name="CART-SERVICE")
public interface CartClient {

    @PostMapping("/cart")
    public Cart addCart(@RequestBody Cart emptyCart);

    @PutMapping("/cart/{cartId}")
    public Cart updateCart(@PathVariable Integer cartId, @RequestBody Cart cart);

 

    @GetMapping("/cart/all")
    public List<Cart> getAllCart();

    @DeleteMapping("/cart/{cartId}")
    public String deleteCart(@PathVariable Integer cartId);

 

    @DeleteMapping("/items/{itemId}")
    public String deleteCartItems(@PathVariable int itemId);

 

    @PostMapping("/items/{cartId}")
    public LineItems addLineItems(@RequestBody LineItems lItems);

 

    @PutMapping("/items/{cartId}")
    public LineItems updateLineItems(@PathVariable Integer cartId, @RequestBody LineItems lItems);

 

    @GetMapping("/items/{cartId}")
    public LineItems getLineItemsById(@PathVariable Integer cartId) ;

    @PostMapping("/cart/emptyCart")
    public Cart createEmptyCart() ;

    @GetMapping("/cart/{cartId}")
    public Cart searchCart(@PathVariable Integer cartId);
}
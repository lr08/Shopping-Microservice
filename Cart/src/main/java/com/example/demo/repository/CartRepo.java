package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Cart;

public interface CartRepo extends JpaRepository<Cart,Integer>{

}

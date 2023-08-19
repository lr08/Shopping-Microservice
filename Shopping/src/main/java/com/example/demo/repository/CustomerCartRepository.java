package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modal.CustomerCart;

public interface CustomerCartRepository extends JpaRepository<CustomerCart,Integer>{

}

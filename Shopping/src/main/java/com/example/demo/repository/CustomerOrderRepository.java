package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modal.CustomerOrder;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder,Integer> {

}

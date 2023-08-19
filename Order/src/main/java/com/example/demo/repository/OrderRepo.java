package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.entity.Orders;

public interface OrderRepo extends MongoRepository<Orders,String> {

}

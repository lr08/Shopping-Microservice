package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.entity.LineItems;

public interface itemRepo extends MongoRepository<LineItems,String> {

}

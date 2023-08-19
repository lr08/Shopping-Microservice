package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.LineItems;

public interface LineItemsRepo extends JpaRepository<LineItems, Integer>{

}

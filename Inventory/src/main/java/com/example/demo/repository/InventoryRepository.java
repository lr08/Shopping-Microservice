package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Integer>{
	

}

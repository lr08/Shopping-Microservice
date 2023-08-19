package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Inventory;
import com.example.demo.exceptions.InventoryNotFoundException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.kafka.AppConstants;
import com.example.demo.modal.Product;
import com.example.demo.proxy.ProductProxy;
import com.example.demo.repository.InventoryRepository;
import com.example.demo.service.payload.InventoryDTO;
import com.example.demo.service.payload.ProdInvDto;

@Service
public class InventoryService {

	@Autowired
	ProductProxy pro;

	@Autowired
	InventoryRepository inventoryRepository;

	private ModelMapper mapper;

	public InventoryService(InventoryRepository inventoryRepository, ModelMapper mapper) {
		super();
		this.inventoryRepository = inventoryRepository;
		this.mapper = mapper;
	}

	// convert entity to DTO
	private InventoryDTO mapToDTO(Inventory inventory) {
		InventoryDTO inventoryDto = mapper.map(inventory, InventoryDTO.class);
		return inventoryDto;
	}

	// convert DTO to entity
	private Inventory mapToEntity(InventoryDTO inventoryDto) {
		Inventory inventory = mapper.map(inventoryDto, Inventory.class);
		return inventory;
	}

	public static final Logger LOGGER = LoggerFactory.getLogger(InventoryService.class);

	@KafkaListener(topics = AppConstants.TOPIC_NAME, groupId = AppConstants.GROUP_ID)
	public void consume(String input) {
		LOGGER.info(String.format("Product id, quantity recived -> %s", input));
		String[] inputs = input.split(",");
		int id = Integer.parseInt(inputs[0]);
		int qty = Integer.parseInt(inputs[1]);
		inventoryRepository.save(new Inventory(id, qty));
	}

	// ADD
	public InventoryDTO addInventory(InventoryDTO inventoryDto) {
		Inventory inventory = mapToEntity(inventoryDto);
		inventory.setQuantity(inventoryDto.getQuantity());
		Inventory newInventory = inventoryRepository.save(inventory);
		// convert entity to DTO
		return mapToDTO(newInventory);
	}

	// UPDATE
	@CachePut(value = "inventory")
	public InventoryDTO updateInventory(int id, InventoryDTO inventoryDto) {
		Inventory inventory = inventoryRepository.findById(id)
				.orElseThrow(() -> new InventoryNotFoundException("Inventory not found"));
		inventory.setQuantity(inventoryDto.getQuantity());
		inventory.setProductId(inventoryDto.getProductId());
		return mapToDTO(inventoryRepository.save(inventory));

	}

	// GET
	@Cacheable(value = "inventory")
	public InventoryDTO searchInventory(int id) {
		Inventory inventory = inventoryRepository.findById(id)
				.orElseThrow(() -> new InventoryNotFoundException("Inventory not found"));
		return mapToDTO(inventory);
	}

	// DELETE
	@CacheEvict(value = "inventory")
	public void deleteInventory(int id) {
		inventoryRepository.deleteById(id);
	}

	// GET
	public List<InventoryDTO> searchAllInventory() {
		List<Inventory> inv = inventoryRepository.findAll();
		return inv.stream().map(inventory -> mapToDTO(inventory)).collect(Collectors.toList());

	}

	// decrease inventory
	// invent quantity, id
	public void decreaseInventory(int id, int qty) {
		InventoryDTO inv = searchInventory(id);
		if (inv.getQuantity() < qty || qty == 0) {
			throw new ResourceNotFoundException("Quantity not available");
		}
		inv.setQuantity(inv.getQuantity() - qty);
		updateInventory(inv.getInventoryId(), inv);
	}

	// product id map with inventory id
	public ProdInvDto getInvWithProduct(int inventoryId, int qty) {
		ProdInvDto productInvDto = new ProdInvDto();
		InventoryDTO inv = searchInventory(inventoryId);
		Product product = pro.searchProduct(inv.getProductId());
		productInvDto = mapper.map(product, ProdInvDto.class);
		productInvDto.setInventoryId(inv.getInventoryId());
		productInvDto.setQuantity(qty);
		productInvDto.getInventoryId();
		productInvDto.getCartId();
		return productInvDto;
	}

}
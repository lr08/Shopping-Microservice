package com.example.demo.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.kafka.AppConstants;
import com.example.demo.payload.ProductDTO;
import com.example.demo.repository.ProductRepository;


@Service

public class ProductService {

	public static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	ProductRepository repo;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	private ModelMapper mapper;

	public ProductService(ProductRepository repo, ModelMapper mapper, KafkaTemplate<String, String> kafkaTemplate) {

		super();

		this.repo = repo;

		this.mapper = mapper;

		this.kafkaTemplate = kafkaTemplate;

	}

	// convert entity to DTO

	private ProductDTO mapToDTO(Product product) {

		ProductDTO productDto = mapper.map(product, ProductDTO.class);

		return productDto;

	}

	// convert DTO to entity

	private Product mapToEntity(ProductDTO productDto) {

		Product product = mapper.map(productDto, Product.class);

		return product;

	}

	public ProductDTO sendProduct(ProductDTO productDto) {

		LOGGER.info(String.format("Product published -> %s", productDto));

		Product product = mapToEntity(productDto);

		Product addedProduct = repo.save(product);

		ProductDTO response = mapToDTO(addedProduct);

		String inventoryInput = addedProduct.getProductId() + "," + productDto.getProductQuantity();

		kafkaTemplate.send(AppConstants.TOPIC_NAME, inventoryInput);

		return response;

	}

	// ADD

	public Product addProduct(ProductDTO productDto) {

		Product product = mapToEntity(productDto);

		Product newProduct = repo.save(product);

		// convert entity to DTO

		return newProduct;

	}

	// DELETE

	@CacheEvict(value = "product")

	public void deleteProduct(int productId) {

		repo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found"));

		repo.deleteById(productId);

	}

	// GET

	@Cacheable(value = "product")

	public Product findProductById(int productId) {

		return repo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found"));

	}

	// GET ALL

	public List<Product> findAllProducts() {

		return repo.findAll();

	}

	// DELETE ALL

	public void deleteAllProducts() {

		repo.deleteAll();

	}

	// UPDATE

	@CachePut(value = "product")

	public Product updateProduct(int id, ProductDTO productDto) {

		Product product = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));

		product.setProductName(productDto.getProductName());

		product.setProductDescription(productDto.getProductDescription());

		product.setProductPrice(productDto.getProductPrice());

		return repo.save(product);

	}

}

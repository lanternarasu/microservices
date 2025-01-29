package com.example.demo.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor // lombok creates a constructor and takes care of constructor injection
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;

	@Override
	public Product addProduct(Product product) {
	
		Product result = productRepository.save(product);
		return result;
	}

	@Override
	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public String deleteProduct(int productId) {
		productRepository.deleteById(productId);
		return "Product Deleted Successfully";
	}

	@Override
	public Product getProductById(int productId) throws ProductNotFoundException {
		Optional<Product> optional = productRepository.findById(productId);
		if (optional.isPresent())
			return optional.get();
		else
			throw new ProductNotFoundException("No Product found with the given Id!!");
	}

	@Override
	public List<Product> getAllProducts() {

		return productRepository.findAll();
	}

	@Override
	public List<Product> getAllProductsBetweenPrice(int initialPrice, int finalPrice) {
		return productRepository.findByProductPriceBetween(initialPrice, finalPrice);
	}

	@Override
	public List<Product> getProductsByCategory(String category) {
		return productRepository.findByProductCategory(category);
	}

	@Override
	public List<Product> getProductsByCategoryAndPrice(String category, int price) {
		return productRepository.findByProductCategoryAndProductPriceLessThan(category, price);
	}

	@Override
	public List<Product> findByProductValidityBefore(LocalDate validDate) {
		return productRepository.findByProductValidityBefore(validDate);
	}

}

package com.example.demo.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.dto.ProductDto;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.model.Product;

public interface ProductService {
	public Product addProduct(Product product);

	public Product updateProduct(Product product);

	public String deleteProduct(int productId);

	public Product getProductById(int productId) throws ProductNotFoundException;

	public List<Product> getAllProducts();

	public List<Product> getAllProductsBetweenPrice(int initialPrice, int finalPrice);
	
	public List<Product> getProductsByCategory(String category);
	
	public List<Product> getProductsByCategoryAndPrice(String category,int price);
	
    public List<Product> findByProductValidityBefore(LocalDate validDate);

}

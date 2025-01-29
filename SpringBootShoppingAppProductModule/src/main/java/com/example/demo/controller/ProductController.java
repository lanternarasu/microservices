package com.example.demo.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProductDto;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	Logger Logger = LoggerFactory.getLogger(this.getClass());

	@PostMapping("/save")
	public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
		Logger.info("In controller /save" + product);
		Product savedProduct = productService.addProduct(product);
		return new ResponseEntity<Product>(savedProduct, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public Product updateProduct(@RequestBody Product product) {
		Logger.info("In controller /update" + product);
		return productService.updateProduct(product);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteProduct(@PathVariable("id") int productId) {
		Logger.info("In controller /delete" + productId);
		return productService.deleteProduct(productId);
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable("id") int productId) throws ProductNotFoundException {
		Logger.info("In controller /getById" + productId);
		Product productEntity = productService.getProductById(productId);
		ProductDto dto = new ProductDto();
		
		dto.setProductId(productEntity.getProductId());
		dto.setProductCategory(productEntity.getProductCategory());
		dto.setProductName(productEntity.getProductName());
		dto.setProductPrice(productEntity.getProductPrice());
		dto.setProductValidity(productEntity.getProductValidity());
		
		return new ResponseEntity<ProductDto>(dto,HttpStatus.OK);
	}

	@GetMapping("/getAllProducts")
	public List<Product> saveProduct() {
		Logger.info("In controller /getAllProducts");
		return productService.getAllProducts();
	}

	@GetMapping("/getAllProductsBetween/{price1}/{price2}")
	public List<Product> getAllProductsBetween(@PathVariable("price1") int initialPrice,
			@PathVariable("price2") int finalPrice) {
		Logger.info("In controller /getAllProductsBetween " + initialPrice + " " + finalPrice);
		return productService.getAllProductsBetweenPrice(initialPrice, finalPrice);
	}

	@GetMapping("/getProductsByCategory/{category}")
	public List<Product> getProductByCategory(@PathVariable("category") String category) {
		Logger.info("In controller /getProductByCategory" + category);
		return productService.getProductsByCategory(category);
	}

	@GetMapping("/getAllProductsByCategoryAndPrice/{category}/{price}")
	public List<Product> getProductByCategoryAndPrice(@PathVariable("category") String category,
			@PathVariable("price") int price) {
		Logger.info("In controller /getAllProductsByCategoryAndPrice " + category + " " + price);
		return productService.getProductsByCategoryAndPrice(category, price);
	}

	@GetMapping("/getValidProducts/{date}")
	public List<Product> getValidProducts(@PathVariable("date") String validDate) {
		Logger.info("In controller /getValidProducts " + validDate);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(validDate, formatter);
		return productService.findByProductValidityBefore(date);
	}
}

package com.example.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.ProductDto;

@FeignClient(url="http://localhost:8083",value = "CART-SERVICE")
public interface APIClient {
	@GetMapping("api/products/getById/{pid}")
	ProductDto getProducts(@PathVariable("pid") int productId);
}

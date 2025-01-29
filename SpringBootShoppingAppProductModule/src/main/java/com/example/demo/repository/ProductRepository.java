package com.example.demo.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	public List<Product> findByProductPriceBetween(int initialPrice,int finalPrice);
	
	public List<Product> findByProductCategory(String category);
	
    public List<Product> findByProductCategoryAndProductPriceLessThan(String category, double price);
    
    public List<Product> findByProductValidityBefore(LocalDate validDate);

}

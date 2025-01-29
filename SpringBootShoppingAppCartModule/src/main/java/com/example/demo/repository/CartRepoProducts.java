package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Cart;
import com.example.demo.model.ProductsInCart;

public interface CartRepoProducts extends JpaRepository<ProductsInCart, Integer>{

}

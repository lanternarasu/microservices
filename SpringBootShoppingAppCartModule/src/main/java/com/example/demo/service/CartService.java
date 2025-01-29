package com.example.demo.service;

import java.util.Optional;

import com.example.demo.dto.ProductDto;
import com.example.demo.model.Cart;

public interface CartService {
	public Cart createCart(Cart cart);
	public void addProductsToCart(int cid,int pid);
	public Optional<Cart> getCart(int cartId);
	public String removeCart(int cartId);
}

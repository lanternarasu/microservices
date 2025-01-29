package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductDto;
import com.example.demo.model.Cart;
import com.example.demo.model.ProductsInCart;
import com.example.demo.repository.CartRepoProducts;
import com.example.demo.repository.CartRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
	
	APIClient apiClient;
	
	CartRepository cartRepository;
	
	CartRepoProducts cartRepoProducts;

	@Override
	public void addProductsToCart(int cid,int pid) {
		ProductDto dto = apiClient.getProducts(pid);
		ProductsInCart inCart = new ProductsInCart();
		
		inCart.setCartId(cid);
		inCart.setProductCategory(dto.getProductCategory());
		inCart.setProductId(dto.getProductId());
		inCart.setProductName(dto.getProductName());
		inCart.setProductPrice(dto.getProductPrice());
		inCart.setProductValidity(dto.getProductValidity());
		
		cartRepoProducts.save(inCart);
		
	}

	@Override
	public  Optional<Cart> getCart(int cartId) {
		return cartRepository.findById(cartId);
	}

	@Override
	public String removeCart(int cartId) {
		cartRepository.deleteById(cartId);
		return "cart deleted successfully";
	}

	@Override
	public Cart createCart(Cart cart) {
		return cartRepository.save(cart);
	}



}

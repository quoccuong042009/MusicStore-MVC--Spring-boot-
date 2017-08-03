package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Cart;
import com.example.demo.repository.CartRepository;

@Service
public class CartService {
	@Autowired
	CartRepository cartService;
	
	public List<Cart> getAll(){
		return cartService.getAllAlbuminCart();
	}
	
	public void updateCartByAlbumId(int albumId){
		cartService.updateCart(albumId);
	}

	public void removeAlbumByCartId(int parseInt) {
		cartService.removeAlbumByCartId(parseInt);
	}
	
	public int getQuantity(){
		return cartService.getQuantity();
	}

	public void deleteAll() {
		cartService.deleteAll();
		
	}
}

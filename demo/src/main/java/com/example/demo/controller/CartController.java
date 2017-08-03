package com.example.demo.controller;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.domain.Cart;
import com.example.demo.service.CartService;
import com.example.demo.service.GenreService;

@Controller
public class CartController {
	CartService cartService;
	
	@Autowired
	GenreService genreService;
	
	@Autowired
	public void CartService(CartService s){
		cartService = s;
	}
	
	@RequestMapping(value = "/ShoppingCart")
	public String cartIndex(Model model){
		int quantity = cartService.getQuantity();
		model.addAttribute("quantity",quantity);
		double total = 0;
		List<Cart> carts = cartService.getAll();
		for(Cart c : carts){
			double price = c.getPrice() * c.getCount();
			total += price;
		}
		NumberFormat formatter = new DecimalFormat("#0.00");     
		model.addAttribute("carts",carts);
		model.addAttribute("total",formatter.format(total));
		return "Cart/cart";
	}
	
	@RequestMapping(value = "/ShoppingCart/onAdding/{albumId}", method = RequestMethod.POST)
	public String onAdd(@PathVariable String albumId, Model model){
		cartService.updateCartByAlbumId(Integer.parseInt(albumId));
		
		return "redirect:/ShoppingCart";
	}
	
	@RequestMapping(value = "/ShoppingCart/onRemove/{cartId}")
	public String onRemove(@PathVariable String cartId, Model model){
		cartService.removeAlbumByCartId(Integer.parseInt(cartId));
		
		return "redirect:/ShoppingCart";
	}
}

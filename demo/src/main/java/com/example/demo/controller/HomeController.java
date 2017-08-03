package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.CartService;
import com.example.demo.service.GenreService;

@Controller
public class HomeController {
	@Autowired
	CartService cartService;
	@Autowired
	GenreService genreService;
	
	@RequestMapping("/")
	public String index(Model model){
		int quantity = cartService.getQuantity();
		model.addAttribute("quantity",quantity);
		model.addAttribute("pageTitle", "Home Page");
		model.addAttribute("genres", genreService.getAll());
		return "index";
	}
	
	
}

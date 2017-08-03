package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.AlbumService;
import com.example.demo.service.ArtistService;
import com.example.demo.service.CartService;
import com.example.demo.service.GenreService;;

@Controller
public class StoreController {
	GenreService genreService;
	AlbumService albumService;
	ArtistService artistService;
	@Autowired
	CartService cartService;
	@Autowired
	public void GenreController(GenreService s) {
		genreService = s;
	}

	@Autowired
	public void AlbumController(AlbumService a) {
		albumService = a;
	}
	
	@Autowired
	public void ArtistService(ArtistService s){
		artistService = s;
	}
	
	@RequestMapping("/Store")
	public String storeIndex(Model model) {
		int quantity = cartService.getQuantity();
		model.addAttribute("quantity",quantity);
		model.addAttribute("pageTitle", "Store");
		model.addAttribute("genres", genreService.getAll());
		return "Store/storeIndex";
	}

	@RequestMapping("/Store/Browse/Genre={g}")
	public String browse(@PathVariable String g, Model model) {
		int quantity = cartService.getQuantity();
		model.addAttribute("quantity",quantity);
		model.addAttribute("pageTitle", "Browse");
		model.addAttribute("content", g);
		model.addAttribute("genres",genreService.getAll());

		int genreId = genreService.getGenreIDbyGenreName(g);
		model.addAttribute("albums", albumService.getAllAlbumByGenreID(Integer.toString(genreId)));

		return "Store/storeBrowse";
	}

	@RequestMapping("/Store/Details/{albumId}")
	public String details(@PathVariable String albumId, Model model) {
		int quantity = cartService.getQuantity();
		model.addAttribute("quantity",quantity);
		model.addAttribute("pageTitle", "Details");
		String albumtitle = albumService.getTitleAlbumByAlbumId(albumId);
		String genre = genreService.getGenreNameByid(albumService.getGenreIdByAlbumId(albumId));
		String artist = artistService.getArtistNameById(albumService.getArtistIdByAlbumId(albumId));
		double price = albumService.getPriceAlbumByAlbumId(albumId);
		
		model.addAttribute("albumId",albumId);
		model.addAttribute("title", albumtitle);
		model.addAttribute("genre", genre);
		model.addAttribute("artist",artist);
		model.addAttribute("price", price);
		model.addAttribute("genres",genreService.getAll());
		
		return "Store/storeDetails";
	}
}

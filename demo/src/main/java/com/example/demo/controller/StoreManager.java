package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.Album;
import com.example.demo.service.AlbumService;
import com.example.demo.service.ArtistService;
import com.example.demo.service.CartService;
import com.example.demo.service.GenreService;

@Controller
public class StoreManager {
	AlbumService albumService;
	GenreService genreService;
	ArtistService artistService;
	@Autowired
	CartService cartService;
	@Autowired
	public void AlbumController(AlbumService a){
		albumService = a;
	}
	
	@Autowired
	public void GenreController(GenreService s){
		genreService = s;
	}
	
	@Autowired
	public void ArtistService(ArtistService s){
		artistService = s;
	}
	
	@RequestMapping("/StoreManager")
	public String StoreManagerIndex(Model model){
		int quantity = cartService.getQuantity();
		model.addAttribute("quantity",quantity);
		model.addAttribute("pageTitle","Store Manager");
		model.addAttribute("content", "Manage The Store");
		model.addAttribute("albums", albumService.getAllAlbum());
		return "StoreManager/storeManager";
	}
	
	@RequestMapping(value = "/StoreManager/Edit/{albumId}", method = RequestMethod.GET)
	public String Edit(@PathVariable String albumId, Model model){
		int quantity = cartService.getQuantity();
		model.addAttribute("quantity",quantity);
		model.addAttribute("Album", new Album());
		// get sub thing
		int genreIdSelected = albumService.getGenreIdByAlbumId(albumId);
		int artistIdSelected = albumService.getArtistIdByAlbumId(albumId);
		
		// get main thing
		String genreSelectedName = genreService.getGenreNameByid(genreIdSelected);
		//String titleSelectedName = albumService.getTitleAlbumByAlbumId(albumId);
		String artistSelectedname = artistService.getArtistNameById(artistIdSelected);
		//double priceSelectedName = albumService.getPriceAlbumByAlbumId(albumId);
		
		// genre
		model.addAttribute("genres", genreService.getAll());
		model.addAttribute("selectedGenre", genreSelectedName);
		
		// artist
		model.addAttribute("artists",artistService.getAll());
		model.addAttribute("selectedArtist", artistSelectedname);
		
		//title
		//model.addAttribute("selectedTitle", titleSelectedName);
		
		//price
		//model.addAttribute("selectedPrice", priceSelectedName);
		
		return "/StoreManager/edit";
	}
	
	/*@RequestMapping(value = "/StoreManager/Edit/{albumId}", method = RequestMethod.POST)
	public String EditSubmit(@PathVariable String albumId
			,@RequestParam("genre") String genreId
			, @RequestParam("artist") String aristId
			, @RequestParam("title") String title
			, @RequestParam("price") String price
			,Model model){
		
		albumService.updateAlbumbyAlbumId(albumId, title, Double.parseDouble(price), Integer.parseInt(aristId), Integer.parseInt(genreId));
		
		model.addAttribute("albums", albumService.getAllAlbum());
		return "redirect:/StoreManager";
	}*/
	////////CHECK EDIT/////////////////////////////////////////
	@RequestMapping(value = "/StoreManager/Edit/{albumId}", method = RequestMethod.POST)
	public String checkOrder(@Valid @ModelAttribute("Album") Album Album,BindingResult bindingResult, @RequestParam("g") String genreId
			, @RequestParam("a") String aristId,@PathVariable String albumId,Model model){

		if (bindingResult.hasErrors()) {
		    List<FieldError> errors = bindingResult.getFieldErrors();
		    for (FieldError error : errors ) {
		        System.out.println (error.getObjectName() + " - " + error.getDefaultMessage());
		    }
			int quantity = cartService.getQuantity();
			model.addAttribute("quantity",quantity);
			// get sub thing
			int genreIdSelected = albumService.getGenreIdByAlbumId(albumId);
			int artistIdSelected = albumService.getArtistIdByAlbumId(albumId);
			
			// get main thing
			String genreSelectedName = genreService.getGenreNameByid(genreIdSelected);
			String artistSelectedname = artistService.getArtistNameById(artistIdSelected);
			
			// genre
			model.addAttribute("genres", genreService.getAll());
			model.addAttribute("selectedGenre", genreSelectedName);
			
			// artist
			model.addAttribute("artists",artistService.getAll());
			model.addAttribute("selectedArtist", artistSelectedname);
			return "/StoreManager/edit";
        }
		else{
			albumService.updateAlbumbyAlbumId(albumId, Album.getTitle(), Album.getPrice(), Integer.parseInt(aristId), Integer.parseInt(genreId));
			
			model.addAttribute("albums", albumService.getAllAlbum());
			return "redirect:/StoreManager";
		}
	}
	
	////////////////////////////////////////////////////////////
	@RequestMapping("/StoreManager/Details/{albumId}")
	public String Details(@PathVariable String albumId, Model model){
		int quantity = cartService.getQuantity();
		model.addAttribute("quantity",quantity);
		// get sub thing
		int genreIdSelected = albumService.getGenreIdByAlbumId(albumId);
		int artistIdSelected = albumService.getArtistIdByAlbumId(albumId);
		
		// get main thing
		String genreSelectedName = genreService.getGenreNameByid(genreIdSelected);
		String titleSelectedName = albumService.getTitleAlbumByAlbumId(albumId);
		String artistSelectedname = artistService.getArtistNameById(artistIdSelected);
		double priceSelectedName = albumService.getPriceAlbumByAlbumId(albumId);
		
		// genre
		model.addAttribute("genre", genreSelectedName);
		
		// artist
		model.addAttribute("artist", artistSelectedname);
		
		//title
		model.addAttribute("title", titleSelectedName);
		
		//price
		model.addAttribute("price", priceSelectedName);
		
		//albumId
		model.addAttribute("albumId", albumId);
		
		return "StoreManager/details";
	}
	
	@RequestMapping("/StoreManager/Delete/{albumId}")
	public String Delete(@PathVariable String albumId, Model model){
		int quantity = cartService.getQuantity();
		model.addAttribute("quantity",quantity);
		// get sub thing
		int genreIdSelected = albumService.getGenreIdByAlbumId(albumId);
		int artistIdSelected = albumService.getArtistIdByAlbumId(albumId);
		
		// get main thing
		String genreSelectedName = genreService.getGenreNameByid(genreIdSelected);
		String titleSelectedName = albumService.getTitleAlbumByAlbumId(albumId);
		String artistSelectedname = artistService.getArtistNameById(artistIdSelected);
		double priceSelectedName = albumService.getPriceAlbumByAlbumId(albumId);
		
		// genre
		model.addAttribute("genre", genreSelectedName);
		
		// artist
		model.addAttribute("artist", artistSelectedname);
		
		//title
		model.addAttribute("title", titleSelectedName);
		
		//price
		model.addAttribute("price", priceSelectedName);
		return "StoreManager/delete";
	}
	
	@RequestMapping( value = "/StoreManager/Delete/{albumId}", method = RequestMethod.POST)
	public String onDelete(@PathVariable String albumId, Model model){
		albumService.deleteAlbumByAlbumId(albumId);
		return "redirect:/StoreManager";
	}
	
	
	@RequestMapping("/StoreManager/Create")
	public String Create(Model model){
		int quantity = cartService.getQuantity();
		model.addAttribute("quantity",quantity);
		model.addAttribute("Album",new Album());
		model.addAttribute("genres",genreService.getAll());
		model.addAttribute("artists",artistService.getAll());
		return "StoreManager/create";
	}
	

	/*@RequestMapping(value = "/StoreManager/OnCreate", method = RequestMethod.POST)
	public String onCreate(@RequestParam("genre") String genreId
			, @RequestParam("artist") String aristId
			, @RequestParam("title") String title
			, @RequestParam("price") String price
			, Model model){
		
		albumService.insertAlbum(title, Double.parseDouble(price)
				, Integer.parseInt(aristId)
				, Integer.parseInt(genreId));
	
		
		return "redirect:/StoreManager";
	}
	*/
	@RequestMapping(value = "/StoreManager/OnCreate", method = RequestMethod.POST)
	public String onCreate(@Valid @ModelAttribute("Album") Album Album,BindingResult bindingResult, @RequestParam("g") String genreId
			, @RequestParam("a") String aristId,Model model){

		if (bindingResult.hasErrors()) {
		    List<FieldError> errors = bindingResult.getFieldErrors();
		    for (FieldError error : errors ) {
		        System.out.println (error.getObjectName() + " - " + error.getDefaultMessage());
		    }
			int quantity = cartService.getQuantity();
			model.addAttribute("quantity",quantity);
			model.addAttribute("genres",genreService.getAll());
			model.addAttribute("artists",artistService.getAll());
			return "StoreManager/create";
	
        }
		else{
			albumService.insertAlbum(Album.getTitle(), Album.getPrice()
					, Integer.parseInt(aristId)
					, Integer.parseInt(genreId));
			
			return "redirect:/StoreManager";
		}
	}
}

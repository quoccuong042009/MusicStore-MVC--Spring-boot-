package com.example.demo.domain;

public class Cart {
	private int CartId;
	private int AlbumId;
	private String Title;
	private double price;
	private int Count;
	
	public Cart(int cartId, int albumId, String title, double price, int count) {
		super();
		CartId = cartId;
		AlbumId = albumId;
		Title = title;
		this.price = price;
		Count = count;
	}

	public int getCartId() {
		return CartId;
	}

	public void setCartId(int cartId) {
		CartId = cartId;
	}

	public int getAlbumId() {
		return AlbumId;
	}

	public void setAlbumId(int albumId) {
		AlbumId = albumId;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCount() {
		return Count;
	}

	public void setCount(int count) {
		Count = count;
	}
	
	
}

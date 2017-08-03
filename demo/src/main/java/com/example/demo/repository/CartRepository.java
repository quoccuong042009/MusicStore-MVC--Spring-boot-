package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Cart;
import com.example.demo.service.AlbumService;

@Repository
public class CartRepository {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	AlbumService albumService;
	
	
	public List<Cart> getAllAlbuminCart(){
		return jdbc.query("select * from cart", (rs, i) -> new Cart(rs.getInt("cart_id"),
				rs.getInt("album_id"),
				rs.getString("title"),
				rs.getDouble("price"),
				rs.getInt("quantity")));
	}
	
	public List<Integer> getAllAlbumId(){
		String sql = "select album_id from cart";
		return jdbc.query(sql, (rs, i) -> new Integer(rs.getInt("album_id")));
	}
	
	public int getQuantityByAlbumId(int albumId){
		String sql = "select quantity from cart where album_id = ?";
		return jdbc.queryForObject(sql, new Object[]{albumId}, Integer.class);
	}
	
	public void updateCart(int albumId){
		List<Integer> albumidContain = this.getAllAlbumId();
		if(albumidContain.contains(albumId)){
			System.out.println("......");
			int curQuantity = this.getQuantityByAlbumId(albumId);
			int newQuantity = curQuantity+1;
			String sql = "update cart set quantity = ? where album_id = ?";
			jdbc.update(sql, new Object[]{newQuantity, albumId});
		}
		else{
			String title = albumService.getTitleAlbumByAlbumId(Integer.toString(albumId));
			double price = albumService.getPriceAlbumByAlbumId(Integer.toString(albumId));
			String sql = "insert into cart (album_id, title, price, quantity) values (?,?,?,?)";
			jdbc.update(sql,new Object[]{albumId,title,price,1});
		}
	}

	public void removeAlbumByCartId(int parseInt) {
		jdbc.update("delete from cart where cart_id = ?", parseInt);
	}

	public int getQuantity() {
		String sql = "select quantity from cart";
		List<Integer> list = jdbc.query(sql, (rs, i) -> new Integer(rs.getInt("quantity")));
		int sum = 0;
		
		for(int i =0; i<list.size();i++){
			sum+=list.get(i);
		}
				
		return sum;
	}

	public void deleteAll() {
		String sql = "truncate cart";
		jdbc.update(sql);
	}
}

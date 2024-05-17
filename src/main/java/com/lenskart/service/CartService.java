package com.lenskart.service;

import com.lenskart.dto.CartDTO;

public interface CartService {
	
	public CartDTO addToCart(int custid,int productid);

	public String deleteProduct(int custid, int prodid);
	

}

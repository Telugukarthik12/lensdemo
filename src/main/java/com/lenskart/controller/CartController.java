package com.lenskart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lenskart.dto.CartDTO;
import com.lenskart.serviceimpl.CartServiceImpl;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "*") // Frontend Connection
public class CartController {

	@Autowired
	CartServiceImpl cartServiceImpl;

	@PostMapping("/add/{customerId}/{productId}")
	public CartDTO addToCart(@PathVariable(value = "customerId") int customerId,
			@PathVariable(value = "productId") int productId) {
		return cartServiceImpl.addToCart(customerId, productId);
	}

	@DeleteMapping("/deleteProducts/{customerId}/{productId}")
	public String deleteProductCart(@PathVariable(value = "customerId") int customerId,
			@PathVariable(value = "productId") int productId) {
		cartServiceImpl.deleteProduct(customerId, productId);
		return "deleted successfully";
	}
//	@PutMapping("/updateCart/{productId}")
//	public String updateProductCart(@PathVariable(value = "productId") int productId) {
//		cartServiceImpl.deleteProduct(productId);
//		return "deleted successfully";
//	}
}

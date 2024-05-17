package com.lenskart.dto;

import lombok.Data;

@Data
public class CartItemDTO {
	private int id;

	private ProductDTO product;
	private int quantity;
}

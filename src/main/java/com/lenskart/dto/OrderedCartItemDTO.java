package com.lenskart.dto;

import lombok.Data;

@Data
public class OrderedCartItemDTO {

	private int id;

	private CartDTO cart;

	private ProductDTO product;
	private int quantity;
}

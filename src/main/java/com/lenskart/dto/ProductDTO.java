package com.lenskart.dto;

import com.lenskart.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

	private int productId;
	private String productName;
	private double productPrice;
	private String productImage;
	private int quantity;
	private Category category;
	private String brand;

}

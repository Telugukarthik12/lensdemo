package com.lenskart.service;

import java.util.List;

import com.lenskart.dto.ProductDTO;

public interface ProductService {
	
	public ProductDTO getById(int id);
	public String updateProduct(int id,ProductDTO productDTO);
	public boolean deleteProduct(int no);
	public ProductDTO addProduct(ProductDTO productDTO);
	public List<ProductDTO> findAll();
	public List<ProductDTO> getProductByBrand(String brandName);
	

}

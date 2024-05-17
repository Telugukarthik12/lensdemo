package com.lenskart.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lenskart.dto.ProductDTO;
import com.lenskart.entity.Product;
import com.lenskart.exception.ProductNotFoundException;
import com.lenskart.repository.ProductRepository;
import com.lenskart.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	public ProductDTO addProduct(ProductDTO productDTO) {
		Product product = new Product();
		product.setBrand(productDTO.getBrand());
		product.setCategory(productDTO.getCategory());
		product.setProductId(productDTO.getProductId());
		product.setProductImage(productDTO.getProductImage());
		product.setProductName(productDTO.getProductName());
		product.setProductPrice(productDTO.getProductPrice());

		productRepository.save(product);
		return productDTO;
	}

	public ProductDTO getById(int id) {
		Product product = productRepository.findById(id).get();

		ProductDTO productDTO = new ProductDTO();
		productDTO.setBrand(product.getBrand());
		productDTO.setCategory(product.getCategory());
		productDTO.setProductId(product.getProductId());
		productDTO.setProductImage(product.getProductImage());
		productDTO.setProductName(product.getProductName());
		productDTO.setProductPrice(product.getProductPrice());

		return productDTO;

	}

	public String updateProduct(int id, ProductDTO productDTO) {

		Product product;
		try {
			product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException());

			if (product.getProductName() != null)
				product.setProductName(productDTO.getProductName());
			if (product.getBrand() != null)
				product.setBrand(productDTO.getBrand());
			if (product.getCategory() != null)
				product.setCategory(productDTO.getCategory());
			if (product.getProductImage() != null)
				product.setProductImage(productDTO.getProductImage());
			if (product.getProductPrice() != 0)
				product.setProductPrice(productDTO.getProductPrice());

			productRepository.save(product);
		} catch (ProductNotFoundException e) {
			System.out.println(e);
			return "Product data not updated";
		}
		return "Product updated Successfully";

	}

	public boolean deleteProduct(int id) {

		productRepository.deleteById(id);
		return true;

	}

	public List<ProductDTO> findAll() {
		List<Product> products = productRepository.findAll();
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		for (Product product : products) {
			ProductDTO productDTO = new ProductDTO();
			productDTO.setBrand(product.getBrand());
			productDTO.setCategory(product.getCategory());
			productDTO.setProductId(product.getProductId());
			productDTO.setProductImage(product.getProductImage());
			productDTO.setProductName(product.getProductName());
			productDTO.setProductPrice(product.getProductPrice());

			productDTOs.add(productDTO);
		}
		return productDTOs;

	}

	public List<ProductDTO> getProductByBrand(String brandName) {

		List<Product> products = productRepository.findByBrand(brandName);

		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();

		for (Product product : products) {
			ProductDTO productDTO = new ProductDTO();

			productDTO.setCategory(product.getCategory());
			productDTO.setBrand(brandName);
			productDTO.setProductId(product.getProductId());
			productDTO.setProductImage(product.getProductImage());
			productDTO.setProductName(product.getProductName());
			productDTO.setProductPrice(product.getProductPrice());

			productDTOs.add(productDTO);
		}

		return productDTOs;

	}

	public List<ProductDTO> getAllProducts() {

		List<Product> products = productRepository.findAll();

		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();

		for (Product product : products) {
			ProductDTO productDTO = new ProductDTO();

			productDTO.setCategory(product.getCategory());
			productDTO.setBrand(product.getBrand());
			productDTO.setProductId(product.getProductId());
			productDTO.setProductImage(product.getProductImage());
			productDTO.setProductName(product.getProductName());
			productDTO.setProductPrice(product.getProductPrice());

			productDTOs.add(productDTO);
		}

		return productDTOs;

	}

	public List<ProductDTO> getByCategoryId(int id) {
		List<Product> products = productRepository.findByProductsByCatogory(id);
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		for (Product product : products) {

			ProductDTO productDTO = new ProductDTO();
			productDTO.setBrand(product.getBrand());
			productDTO.setCategory(product.getCategory());
			productDTO.setProductId(product.getProductId());
			productDTO.setProductImage(product.getProductImage());
			productDTO.setProductName(product.getProductName());
			productDTO.setProductPrice(product.getProductPrice());

			productDTOs.add(productDTO);
		}

		return productDTOs;

	}

	public void linkImageToProduct(int productId, String imagePath) {
		// TODO Auto-generated method stub
		Product product = productRepository.findById(productId).get();
		product.setProductImage(imagePath);

		productRepository.save(product);

	}

}

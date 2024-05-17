package com.lenskart.serviceimpl;
//

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lenskart.dto.CartDTO;
import com.lenskart.dto.CartItemDTO;
import com.lenskart.dto.ProductDTO;
import com.lenskart.dto.UserDTO;
import com.lenskart.entity.Cart;
import com.lenskart.entity.CartItem;
import com.lenskart.entity.CustomerEntity;
import com.lenskart.entity.Product;
import com.lenskart.exception.AddToCartNotFoundException;
import com.lenskart.exception.CustomerNotFoundException;
import com.lenskart.repository.CartRepository;
import com.lenskart.repository.CustomerRepository;
import com.lenskart.repository.ProductRepository;
import com.lenskart.service.CartService;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CartRepository cartRepository;

	public CartDTO addToCart(int customerId, int productId) {
		double total;
		int quantity;
		CustomerEntity customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new AddToCartNotFoundException());

		Product product = productRepository.findById(productId).orElseThrow(() -> new AddToCartNotFoundException());
		CartDTO cartDTO = new CartDTO();
		Cart cart = customer.getCart();

		if (cart == null) {
			cart = new Cart();
			cart.setCustomer(customer);
			customer.setCart(cart);
		}

		CartItem cartItem = cart.getCartItems().stream().filter(item -> item.getProduct().equals(product)).findFirst()
				.orElse(null);

		if (cartItem != null) {
			cartItem.setQuantity(cartItem.getQuantity() + 1);
		} else {
			cartItem = new CartItem();
			cartItem.setProduct(product);
			cartItem.setQuantity(1);
			cartItem.setCart(cart);
			cart.getCartItems().add(cartItem);
		}
		total = cart.getTotalPrice();

		quantity = cart.getTotalQuantity();
		quantity = quantity + 1;
		total = product.getProductPrice() + total;
		cart.setTotalPrice(total);
		cart.setTotalQuantity(quantity);

		cartRepository.save(cart);

		UserDTO userDTO = new UserDTO();

		userDTO.setAddress(customer.getAddress());
		userDTO.setEmail(customer.getEmail());
		userDTO.setNumber(customer.getNumber());
		userDTO.setPassword(customer.getPassword());
		userDTO.setId(customer.getId());
		userDTO.setUsername(customer.getUsername());

		cartDTO.setCustomer(userDTO);

		List<CartItemDTO> cartItemDTOs = new ArrayList<>();
		for (CartItem product_i : cart.getCartItems()) {
			CartItemDTO cartItemDTO = new CartItemDTO();

			cartItemDTO.setProduct(mapProductsToDTO(product_i.getProduct()));
			cartItemDTO.setQuantity(product_i.getQuantity());
			cartItemDTOs.add(cartItemDTO);
		}
		cartDTO.setCartItems(cartItemDTOs);
		cartDTO.setTotalPrice(total);
		cartDTO.setTotalQuantity(quantity);
		return cartDTO;

	}

	private ProductDTO mapProductsToDTO(Product product) {

		ProductDTO productDTO = new ProductDTO();

		productDTO.setBrand(product.getBrand());
		productDTO.setCategory(product.getCategory());
		productDTO.setProductId(product.getProductId());
		productDTO.setProductImage(product.getProductImage());
		productDTO.setProductName(product.getProductName());
		productDTO.setProductPrice(product.getProductPrice());

		return productDTO;

	}

	public String deleteProduct(int custid, int prodid) {

		CustomerEntity customer;
		try {
			customer = customerRepository.findById(custid).orElseThrow(() -> new CustomerNotFoundException());

			Cart cart = customer.getCart();

			List<CartItem> cartItems = cart.getCartItems();

			int quantity;
			double totalprice;
			quantity = cart.getTotalQuantity();
			totalprice = cart.getTotalPrice();
			for (CartItem cartItem : cartItems) {

				Product product = cartItem.getProduct();

				if (product.getProductId() == prodid) {
					quantity = quantity - 1;
					totalprice = totalprice - product.getProductPrice();
					cartItem.setQuantity(cartItem.getQuantity() - 1);
				}
			}
			cart.setTotalPrice(totalprice);
			cart.setTotalQuantity(quantity);
			cartRepository.save(cart);

			return "deleted Successfully";
		} catch (CustomerNotFoundException e) {
			System.out.println(e);
			return "Customer not Found";
		}

	}
}
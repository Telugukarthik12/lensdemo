package com.lenskart.service;

import java.util.List;

import com.lenskart.dto.CartDTO;
import com.lenskart.dto.UserDTO;

public interface CustomerService {

	public UserDTO registerCustomer(UserDTO customerDTO);

	public String updateCustomer(int id, UserDTO customerDTO);

	public boolean deleteCustomer(int id);

	public List<UserDTO> getByEmail(String email);

	public CartDTO getCartByCustomer(int customerId);

}

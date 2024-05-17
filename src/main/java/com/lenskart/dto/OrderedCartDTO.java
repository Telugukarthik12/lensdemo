package com.lenskart.dto;

import java.util.List;
import lombok.Data;

@Data
public class OrderedCartDTO {

	private int id;
	private int orderid;

	private List<OrderedCartItemDTO> cartItems;

	private UserDTO customer;

	private int totalQuantity;
	private double totalPrice;

}

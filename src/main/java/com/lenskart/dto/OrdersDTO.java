package com.lenskart.dto;

import java.time.LocalDateTime;
import com.lenskart.entity.Status;

import lombok.Data;

@Data
public class OrdersDTO {

	private int orderId;
	private LocalDateTime date;
	private Status status;
	private CartDTO cart;
	private OrderedCartDTO orderedCartDTO;

}

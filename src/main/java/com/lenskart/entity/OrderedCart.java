package com.lenskart.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "orderedCart")
public class OrderedCart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToMany(mappedBy = "orderedcart", cascade = CascadeType.ALL)
	private List<OrderedCartItems> cartItems = new ArrayList<>();

	@OneToOne
	@JoinColumn(name = "customer_id")
	private CustomerEntity customer;

	private int totalQuantity;
	private double totalPrice;
	private int orderid;

}

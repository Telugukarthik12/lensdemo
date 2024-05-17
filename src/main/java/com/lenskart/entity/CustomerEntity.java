package com.lenskart.entity;

import javax.persistence.Entity;

@Entity
public class CustomerEntity extends UserEntity {

	public CustomerEntity(int id, String username, String password, String email, int number, String address) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.number = number;
		this.address = address;
	}

	public CustomerEntity() {
		// TODO Auto-generated constructor stub
	}

}

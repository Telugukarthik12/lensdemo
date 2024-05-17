package com.lenskart.entity;

import javax.persistence.Entity;

@Entity

public class AdminEntity extends UserEntity {
	public AdminEntity(int id, String username, String password, String email, int number, String address) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.number = number;
		this.address = address;
	}

	public AdminEntity() {
		// TODO Auto-generated constructor stub
	}
}

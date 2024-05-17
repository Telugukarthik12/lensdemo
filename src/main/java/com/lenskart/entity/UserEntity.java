package com.lenskart.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Inheritance
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "userstable")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
	@Column
	protected String username;
	@Column
	protected String password;
	@Column
	protected String email;
	@Column
	protected long number;
	@Column
	protected String address;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "customer")
	protected Cart cart;

	public UserEntity(int id, String username, String password, String email, long number, String address) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.number = number;
		this.address = address;
	}

}

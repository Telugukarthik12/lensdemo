package com.lenskart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lenskart.entity.CustomerEntity;
@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer>{
	
	public List<CustomerEntity> findByEmail(String email);

}

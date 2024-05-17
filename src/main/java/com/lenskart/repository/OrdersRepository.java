package com.lenskart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lenskart.entity.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {

}

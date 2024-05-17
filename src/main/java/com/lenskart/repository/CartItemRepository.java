package com.lenskart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lenskart.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
}

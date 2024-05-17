package com.lenskart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lenskart.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

	public Optional<Cart> findByCustomerId(int customerId);

	@Modifying
	@Transactional
	@Query(value = "delete from carts where customer_id = ?1 ", nativeQuery = true)
	public void deleteByCustomerUserid(int id);

	@Modifying
	@Transactional
	@Query(value = "delete from cart_product where product_id = ?1 ", nativeQuery = true)
	public void deleteByCartProduct(int id);

	@Modifying
	@Transactional
	@Query(value = "delete from cartitems where cart_id = ?1 ", nativeQuery = true)
	public void deleteCartItemsByCartId(int id);

}

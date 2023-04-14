package com.gogreen.apis.cart.repository;

import com.gogreen.models.cart.entities.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepositiry extends JpaRepository<CartItemEntity, Long> {

}

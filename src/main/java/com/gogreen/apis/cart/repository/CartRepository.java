package com.gogreen.apis.cart.repository;

import com.gogreen.models.cart.entities.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {

	Optional<CartEntity> findByUserId(Long userId);
}

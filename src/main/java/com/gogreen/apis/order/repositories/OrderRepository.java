package com.gogreen.apis.order.repositories;

import com.gogreen.models.order.entities.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
	Page<OrderEntity> findByUserId(Long id, Pageable pageable);
}


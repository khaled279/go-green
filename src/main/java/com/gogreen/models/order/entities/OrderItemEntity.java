package com.gogreen.models.order.entities;

import com.gogreen.models.base.entity.SuperBaseEntity;
import com.gogreen.models.cart.entities.CartEntity;
import com.gogreen.models.product.entities.ProductEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "order_items")
@SuperBuilder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemEntity extends SuperBaseEntity {
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private OrderEntity order;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private ProductEntity product;

	private Integer quantity;
}

package com.gogreen.models.cart.entities;

import com.gogreen.models.base.entity.SuperBaseEntity;
import com.gogreen.models.product.entities.ProductEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart_item")
@SuperBuilder
public class CartItemEntity extends SuperBaseEntity {
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cart_id")
	private CartEntity cartEntity;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private ProductEntity product;

	private Integer quantity;
}

package com.gogreen.models.cart.dto;

import com.gogreen.models.cart.entities.CartItemEntity;

import java.math.BigDecimal;
import java.util.Set;

public class CartDto {
	private BigDecimal total;

	private Set<CartItemDto> items;
}

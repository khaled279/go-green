package com.gogreen.models.cart.dto;

import com.gogreen.models.product.dtos.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CartItemDto {
	private ProductDto product;

	private Integer quantity;

}

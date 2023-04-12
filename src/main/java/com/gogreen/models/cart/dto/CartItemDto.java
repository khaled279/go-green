package com.gogreen.models.cart.dto;

import com.gogreen.models.base.dto.SuperBaseDto;
import com.gogreen.models.base.entity.SuperBaseEntity;
import com.gogreen.models.product.dtos.ProductDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
public class CartItemDto extends SuperBaseDto {
	private ProductDto product;

	private Integer quantity;

}

package com.gogreen.models.order.dtos;

import com.gogreen.models.base.dto.SuperBaseDto;
import com.gogreen.models.product.dtos.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto extends SuperBaseDto {

	private ProductDto product;

	private Integer quantity;
}

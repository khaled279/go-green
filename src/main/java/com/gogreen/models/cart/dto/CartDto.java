package com.gogreen.models.cart.dto;

import com.gogreen.models.base.dto.SuperBaseDto;
import com.gogreen.models.cart.entities.CartItemEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Setter
@Getter
public class CartDto extends SuperBaseDto {
	private BigDecimal total;

	private Set<CartItemDto> items;
}

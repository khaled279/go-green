package com.gogreen.models.product.dtos;

import com.gogreen.models.base.dto.BaseDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@SuperBuilder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto extends BaseDto {
	private String name;
	private String description;
	private BigDecimal price;
	private BigDecimal discountedPrice;
	private Integer quantity;

	private Float rating;
	@Builder.Default
	private boolean discounted = false;

	private Long productCategoryId;
	private String productCategoryName;

}

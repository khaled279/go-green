package com.gogreen.models.product.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductSearchDto {
	private List<Long> productCategoryIds;
	private String productName;
	private BigDecimal maximumPrice;

	private BigDecimal minimumPrice;

}

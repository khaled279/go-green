package com.gogreen.models.product.dtos;

import com.gogreen.models.base.dto.SuperBaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ProductCategoryDto extends SuperBaseDto {
	private String name;
}

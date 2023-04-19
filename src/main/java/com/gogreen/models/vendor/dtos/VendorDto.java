package com.gogreen.models.vendor.dtos;

import com.gogreen.models.base.dto.BaseDto;
import com.gogreen.models.product.dtos.ProductDto;
import com.gogreen.models.product.entities.ProductEntity;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class VendorDto extends BaseDto {
	@NotBlank(message = "Vendor Name Can't be Empty")
	private String name;
	private String description;
	private String vision;
	private String mission;
	private Set<ProductDto> products;
}

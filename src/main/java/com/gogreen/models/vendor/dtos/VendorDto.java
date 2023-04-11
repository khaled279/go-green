package com.gogreen.models.vendor.dtos;

import com.gogreen.models.product.dtos.ProductDto;
import com.gogreen.models.product.entities.ProductEntity;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.Set;

public class VendorDto {

	private String name;
	private String description;
	private String vision;
	private String mission;
	private Set<ProductDto> products;
}

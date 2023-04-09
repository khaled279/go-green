package com.gogreen.apis.product.boundaries.helper;

import com.gogreen.core.mappers.GenericMapper;
import com.gogreen.models.product.dtos.ProductDto;
import com.gogreen.models.product.entities.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper extends GenericMapper<ProductEntity, ProductDto> {

	@Override
	@Mapping(target = "productCategoryName", source = "productCategory.name")
	@Mapping(target = "productCategoryId", source = "productCategory.id")
	ProductDto toDto(ProductEntity entity);
}

package com.gogreen.apis.lookups.boundary.util.mapper;

import com.gogreen.core.mappers.GenericMapper;
import com.gogreen.models.product.dtos.ProductCategoryDto;
import com.gogreen.models.product.entities.ProductCategoryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductCategoryMapper
		extends GenericMapper<ProductCategoryEntity, ProductCategoryDto> {
	@Override
	ProductCategoryEntity toEntity(ProductCategoryDto dto);

	@Override
	ProductCategoryDto toDto(ProductCategoryEntity entity);

	@Override
	List<ProductCategoryDto> toDtoList(List<ProductCategoryEntity> entityList);
}

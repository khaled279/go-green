package com.gogreen.apis.cart.boundary.util.mapper;

import com.gogreen.core.mappers.GenericMapper;
import com.gogreen.models.cart.dto.CartDto;
import com.gogreen.models.cart.entities.CartEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper extends GenericMapper<CartEntity, CartDto> {
	@Override
	CartEntity toEntity(CartDto dto);

	@Override
	CartDto toDto(CartEntity entity);
}

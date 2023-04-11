package com.gogreen.apis.cart.boundary.util.mapper;

import com.gogreen.apis.product.boundaries.helper.ProductMapper;
import com.gogreen.apis.product.boundaries.helper.ProductMapperImpl;
import com.gogreen.core.mappers.GenericMapper;
import com.gogreen.models.cart.dto.CartItemDto;
import com.gogreen.models.cart.entities.CartItemEntity;
import com.gogreen.models.product.dtos.ProductDto;
import com.gogreen.models.product.entities.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CartItemMapper extends GenericMapper<CartItemEntity, CartItemDto> {
	@Override
	CartItemDto toDto(CartItemEntity entity);

	@Override
	CartItemEntity toEntity(CartItemDto dto);

	@Override
	List<CartItemDto> toDtoList(List<CartItemEntity> entityList);

	default Set<ProductDto> productEntitySetToDtoSet(Set<ProductEntity> productEntities) {
		return productEntities.stream()
				.map((productEntity) -> new ProductMapperImpl().toDto(productEntity))
				.collect(Collectors.toSet());
	}
}

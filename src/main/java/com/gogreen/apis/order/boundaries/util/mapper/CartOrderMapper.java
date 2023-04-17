package com.gogreen.apis.order.boundaries.util.mapper;

import com.gogreen.models.cart.entities.CartEntity;
import com.gogreen.models.order.entities.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartOrderMapper {
	@Mapping(target = "id", source = "id", ignore = true)
	OrderEntity toOrderEntity(CartEntity cartEntity);

	@Mapping(target = "id", source = "id", ignore = true)
	CartEntity toOrderEntity(OrderEntity cartEntity);
}

package com.gogreen.apis.order.boundaries.util.mapper;

import com.gogreen.models.cart.entities.CartItemEntity;
import com.gogreen.models.order.entities.OrderItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartItemOrderItemMapper {
	@Mapping(target = "id", source = "id", ignore = true)
	OrderItemEntity toOrderItemEntity(CartItemEntity cartItem);

	@Mapping(target = "id", source = "id", ignore = true)
	CartItemEntity toOrderItemEntity(OrderItemEntity orderItem);
}

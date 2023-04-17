package com.gogreen.apis.order.boundaries.util.mapper;

import com.gogreen.core.mappers.GenericMapper;
import com.gogreen.models.order.dtos.OrderItemDto;
import com.gogreen.models.order.entities.OrderItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper extends GenericMapper<OrderItemEntity, OrderItemDto> {
	@Override
	OrderItemEntity toEntity(OrderItemDto dto);

	@Override
	OrderItemDto toDto(OrderItemEntity entity);

	@Override
	List<OrderItemDto> toDtoList(List<OrderItemEntity> entityList);
}

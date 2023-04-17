package com.gogreen.apis.order.boundaries.util.mapper;

import com.gogreen.core.mappers.GenericMapper;
import com.gogreen.models.order.dtos.OrderDto;
import com.gogreen.models.order.entities.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper extends GenericMapper<OrderEntity, OrderDto> {
	@Override
	OrderEntity toEntity(OrderDto dto);

	@Override
	@Mapping(source = "items", target = "items")
	OrderDto toDto(OrderEntity entity);

	@Override
	List<OrderDto> toDtoList(List<OrderEntity> entityList);
}

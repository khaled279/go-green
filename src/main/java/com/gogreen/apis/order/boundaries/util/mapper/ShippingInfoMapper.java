package com.gogreen.apis.order.boundaries.util.mapper;

import com.gogreen.core.mappers.GenericMapper;
import com.gogreen.models.base.dto.ShippingInfoDto;
import com.gogreen.models.base.entity.ShippingInfo;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface ShippingInfoMapper extends GenericMapper<ShippingInfo, ShippingInfoDto> {
	@Override
	ShippingInfo toEntity(ShippingInfoDto dto);

	@Override
	ShippingInfoDto toDto(ShippingInfo entity);

	@Override
	List<ShippingInfoDto> toDtoList(List<ShippingInfo> entityList);
}

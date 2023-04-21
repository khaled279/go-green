package com.gogreen.apis.admin.vendor.user.boundary.util.mapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gogreen.core.constants.Constants;
import com.gogreen.core.mappers.GenericMapper;
import com.gogreen.models.user.dtos.VendorUserWrapperDto;
import com.gogreen.models.user.entities.VendorUserWrapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VendorUserWrapperMapper
		extends GenericMapper<VendorUserWrapper, VendorUserWrapperDto> {
	@Override
	VendorUserWrapper toEntity(VendorUserWrapperDto dto);

	@Override
	@Mapping(target = "goGreenUser.password", ignore = true)
	VendorUserWrapperDto toDto(VendorUserWrapper entity);

	@Override
	List<VendorUserWrapperDto> toDtoList(List<VendorUserWrapper> entityList);
}

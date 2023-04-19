package com.gogreen.apis.admin.vendor.boundary.util.mapper;

import com.gogreen.core.constants.Constants;
import com.gogreen.core.mappers.GenericMapper;
import com.gogreen.models.vendor.dtos.VendorDto;
import com.gogreen.models.vendor.entities.VendorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VendorMapper extends GenericMapper<VendorEntity, VendorDto> {
	@Override
	VendorEntity toEntity(VendorDto dto);

	@Override
	VendorDto toDto(VendorEntity entity);

	@Override
	List<VendorDto> toDtoList(List<VendorEntity> entityList);
}

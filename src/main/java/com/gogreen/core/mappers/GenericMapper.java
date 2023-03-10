package com.gogreen.core.mappers;

import com.gogreen.core.constants.Constants;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

public interface GenericMapper<E, D> {
	@Mapping(target = "createdAt", source = "createdAt", dateFormat = Constants.DATE_FORMAT)
	@Mapping(target = "lastUpdatedAt", source = "lastUpdatedAt", dateFormat = Constants.DATE_FORMAT)
	E toEntity(D dto);

	D toDto(E entity);
}

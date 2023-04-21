package com.gogreen.apis.user.boundary.util.mapper;

import com.gogreen.core.constants.Constants;
import com.gogreen.core.mappers.GenericMapper;
import com.gogreen.models.auth.dtos.GoGreenUserDto;
import com.gogreen.models.auth.entities.GoGreenUserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Mapper(componentModel = "spring")
public interface GoGreenUserMapper
		extends GenericMapper<GoGreenUserEntity, GoGreenUserDto> {

	@Override
	GoGreenUserEntity toEntity(GoGreenUserDto dto);

	@Override
	@Mapping(target = "password", source = "password", ignore = true)
	GoGreenUserDto toDto(GoGreenUserEntity entity);

	default Timestamp map(final String value) throws ParseException {
		return value == null ?
				null :
				new Timestamp(new SimpleDateFormat(Constants.DATE_FORMAT).parse(value)
						.getTime());
	}
}

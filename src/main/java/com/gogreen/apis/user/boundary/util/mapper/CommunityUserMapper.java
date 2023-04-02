package com.gogreen.apis.user.boundary.util.mapper;

import com.gogreen.core.constants.Constants;
import com.gogreen.core.mappers.GenericMapper;
import com.gogreen.models.auth.dtos.GoGreenUserDto;
import com.gogreen.models.auth.entities.GoGreenUserEntity;
import com.gogreen.models.user.dtos.CommunityUserDto;
import com.gogreen.models.user.entities.CommunityUserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Mapper(componentModel = "spring")
public interface CommunityUserMapper
		extends GenericMapper<CommunityUserEntity, CommunityUserDto> {

	@Override
	CommunityUserEntity toEntity(CommunityUserDto dto);

	@Override
	CommunityUserDto toDto(CommunityUserEntity entity);

	default Timestamp map(final String value) throws ParseException {
		return value == null ?
				null :
				new Timestamp(new SimpleDateFormat(Constants.DATE_FORMAT).parse(value)
						.getTime());
	}
}

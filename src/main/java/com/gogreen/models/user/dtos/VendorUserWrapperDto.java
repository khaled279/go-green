package com.gogreen.models.user.dtos;

import com.gogreen.models.auth.dtos.GoGreenUserDto;
import com.gogreen.models.auth.entities.GoGreenUserEntity;
import com.gogreen.models.user.entities.VendorUserEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VendorUserWrapperDto {
	private GoGreenUserDto goGreenUser;
	private VendorUserDto vendorUser;
}

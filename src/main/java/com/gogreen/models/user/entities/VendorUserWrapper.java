package com.gogreen.models.user.entities;

import com.gogreen.models.auth.entities.GoGreenUserEntity;
import com.gogreen.models.user.entities.VendorUserEntity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class VendorUserWrapper {

	private GoGreenUserEntity goGreenUser;
	private VendorUserEntity vendorUser;
}

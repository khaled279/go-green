package com.gogreen.apis.admin.vendor.user.boundary;

import com.gogreen.apis.admin.vendor.user.control.VendorUserService;
import com.gogreen.models.auth.dtos.AuthenticationResponseDto;
import com.gogreen.models.auth.dtos.GoGreenUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/vendor")
@RequiredArgsConstructor
public class VendorUserApi {
	private final VendorUserService vendorUserService;

	@PostMapping("/{id}/user")
	@PreAuthorize("hasAuthority('vendor_user_create')")
	AuthenticationResponseDto createVendorUser(@PathVariable(name = "id") Long vendorId,
			@RequestBody GoGreenUserDto goGreenUserDto) {
		return this.vendorUserService.createUser(vendorId, goGreenUserDto);
	}
}

package com.gogreen.apis.admin.vendor.user.boundary;

import com.gogreen.apis.admin.vendor.user.control.VendorUserService;
import com.gogreen.models.auth.dtos.AuthenticationResponseDto;
import com.gogreen.models.auth.dtos.GoGreenUserDto;
import com.gogreen.models.auth.dtos.UserDetailsImpl;
import com.gogreen.models.user.dtos.VendorUserWrapperDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/vendor")
@RequiredArgsConstructor
public class VendorUserApi {
	private final VendorUserService vendorUserService;

	@PostMapping("/{id}/user")
	@PreAuthorize("hasAuthority('vendor_user_create')")
	AuthenticationResponseDto createVendorUser(@PathVariable(name = "id") Long vendorId,
			@RequestBody GoGreenUserDto goGreenUserDto,
			@AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
		return this.vendorUserService.createUser(vendorId, goGreenUserDto,
				userDetailsImpl);
	}

	@GetMapping("/{id}/user")
	@PreAuthorize("hasAuthority('vendor_user_list')")
	Page<VendorUserWrapperDto> listVendorUsers(@PathVariable(name = "id") Long vendorId,
			@RequestParam(required = false) Long pageSize,
			@RequestParam(required = false, defaultValue = "0") Long pageNo,
			@RequestParam(required = false, defaultValue = "ASC") Sort.Direction direction,
			@AuthenticationPrincipal UserDetailsImpl userDetails) {
		return this.vendorUserService.listVendorUser(vendorId, pageSize == null ?
				Pageable.unpaged() :
				PageRequest.of(pageNo.intValue(), pageSize.intValue()), userDetails);
	}

}

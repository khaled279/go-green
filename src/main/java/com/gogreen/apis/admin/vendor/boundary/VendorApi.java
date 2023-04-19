package com.gogreen.apis.admin.vendor.boundary;

import com.gogreen.apis.admin.vendor.control.VendorService;
import com.gogreen.models.auth.dtos.UserDetailsImpl;
import com.gogreen.models.vendor.dtos.VendorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("admin/vendor")
@RequiredArgsConstructor
public class VendorApi {
	private final VendorService vendorService;

	@PostMapping("")
	@PreAuthorize("hasAuthority('vendor_create')")
	ResponseEntity<VendorDto> createVendor(@RequestBody @Valid VendorDto vendorDto) {
		return ResponseEntity.ok(this.vendorService.createVendor(vendorDto));
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('vendor_view')")
	ResponseEntity<VendorDto> retrieveVendor(@PathVariable Long id,
			@AuthenticationPrincipal UserDetailsImpl userDetails) {

		return ResponseEntity.ok(this.vendorService.retrieveVendor(id));
	}

	@GetMapping("")
	@PreAuthorize("hasAuthority('vendor_list')")
	ResponseEntity<Page<VendorDto>> listVendors(
			@RequestParam(required = false) Long pageSize,
			@RequestParam(required = false, defaultValue = "0") Long pageNo,
			@RequestParam(required = false, defaultValue = "ASC") Sort.Direction direction) {
		return ResponseEntity.ok(this.vendorService.listVendors(pageSize == null ?
				Pageable.unpaged() :
				PageRequest.of(pageNo.intValue(), pageSize.intValue())));
	}

	@PutMapping("")
	@PreAuthorize("hasAuthority('vendor_update')")
	ResponseEntity<VendorDto> updateVendor(@RequestBody @Valid VendorDto vendorDto) {
		return ResponseEntity.ok(this.vendorService.updateVendor(vendorDto));
	}
}

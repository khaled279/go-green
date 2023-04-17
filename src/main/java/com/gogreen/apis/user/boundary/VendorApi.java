package com.gogreen.apis.user.boundary;

import com.gogreen.models.vendor.dtos.VendorDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("vendor")
public class VendorApi {

	@PostMapping("")
	ResponseEntity<VendorDto> createVendor(@RequestBody VendorDto vendorDto) {
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{id}")
	ResponseEntity<VendorDto> retrieveVendor(@PathVariable Long id) {
		return ResponseEntity.ok().build();
	}

	@GetMapping("")
	@PreAuthorize("hasAuthority('list_vendors')")
	ResponseEntity<Page<VendorDto>> listVendors(
			@RequestParam(required = false) Long pageSize,
			@RequestParam(required = false, defaultValue = "0") Long pageNo,
			@RequestParam(required = false, defaultValue = "ASC") Sort.Direction direction) {
		return ResponseEntity.ok().build();
	}

	@PutMapping("")
	ResponseEntity<VendorDto> updateVendor(@RequestBody VendorDto vendorDto) {
		return ResponseEntity.ok().build();
	}
}

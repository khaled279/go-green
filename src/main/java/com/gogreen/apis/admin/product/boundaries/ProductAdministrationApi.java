package com.gogreen.apis.admin.product.boundaries;

import com.gogreen.apis.admin.product.control.ProductAdministrationService;
import com.gogreen.core.exception.SystemException;
import com.gogreen.models.auth.dtos.UserDetailsImpl;
import com.gogreen.models.auth.enums.UserTypeEnum;
import com.gogreen.models.product.dtos.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("admin/vendor")
public class ProductAdministrationApi {

	private final ProductAdministrationService productAdministrationService;

	@GetMapping("/product")
	Page<ProductDto> listProducts(@RequestParam(required = false) Long vendorId,
			@RequestParam(required = false) Long pageSize,
			@RequestParam(required = false, defaultValue = "0") Long pageNo,
			@RequestParam(required = false, defaultValue = "ASC") Sort.Direction direction) {
		return this.productAdministrationService.listProducts(vendorId, pageSize == null ?
				Pageable.unpaged() :
				PageRequest.of(pageNo.intValue(), pageSize.intValue()));
	}

	@PostMapping("/product")
	@PreAuthorize("hasAuthority('product_create')")
	ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto,
			@RequestParam(required = false) Long vendorId,
			@AuthenticationPrincipal UserDetailsImpl userDetails) {

		return ResponseEntity.ok(
				this.productAdministrationService.createProduct(productDto, vendorId,
						userDetails));
	}
}

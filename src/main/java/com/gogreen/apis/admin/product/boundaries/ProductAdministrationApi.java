package com.gogreen.apis.admin.product.boundaries;

import com.gogreen.apis.admin.product.control.ProductAdministrationService;
import com.gogreen.models.product.dtos.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
}

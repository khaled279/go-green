package com.gogreen.apis.product.boundaries;

import com.gogreen.apis.product.control.ProductService;
import com.gogreen.models.product.dtos.ProductDto;
import com.gogreen.models.product.dtos.ProductSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductApi {
	private final ProductService productService;

	@GetMapping
	Page<ProductDto> searchProducts(
			@RequestParam(required = false) ProductSearchDto productSearchDto,
			@RequestParam(required = false) Long pageSize,
			@RequestParam(required = false, defaultValue = "0") Long pageNo,
			@RequestParam(required = false, defaultValue = "ASC") Sort.Direction direction) {

		return this.productService.searchProducts(productSearchDto, pageSize == null ?
				Pageable.unpaged() :
				PageRequest.of(pageNo.intValue(), pageSize.intValue()));
	}
}

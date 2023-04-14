package com.gogreen.apis.lookups.boundary;

import com.gogreen.apis.lookups.control.ProductCategoryService;
import com.gogreen.models.product.dtos.ProductCategoryDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("go-green/lk")
@RequiredArgsConstructor
public class ProductCategoryApi {
	private final ProductCategoryService productCategoryService;

	@GetMapping("/product-category")
	ResponseEntity<List<ProductCategoryDto>> retrieveProductCategories() {
		return ResponseEntity.ok(this.productCategoryService.retrieveProductCategories());
	}
}

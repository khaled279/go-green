package com.gogreen.apis.admin.product.control;

import com.gogreen.apis.product.boundaries.helper.ProductMapper;
import com.gogreen.apis.product.control.ProductService;
import com.gogreen.apis.product.repositories.ProductRepository;
import com.gogreen.models.product.dtos.ProductDto;
import com.gogreen.models.product.dtos.ProductSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.PublicKey;

@Service
@RequiredArgsConstructor
public class ProductAdministrationService {
	private final ProductRepository productRepository;
	private final ProductMapper productMapper;

	private final ProductService productService;

	public Page<ProductDto> listProducts(Long vendorId, Pageable pageable) {
		ProductSearchDto productSearchDto = new ProductSearchDto();
		productSearchDto.setVendorId(vendorId);
		return this.productService.searchProducts(productSearchDto, pageable);
	}
}

package com.gogreen.apis.admin.product.control;

import com.gogreen.apis.admin.vendor.main.repository.VendorRepository;
import com.gogreen.apis.admin.vendor.user.repository.VendorUserRepository;
import com.gogreen.apis.product.boundaries.helper.ProductMapper;
import com.gogreen.apis.product.control.ProductService;
import com.gogreen.apis.product.repositories.ProductRepository;
import com.gogreen.core.exception.SystemException;
import com.gogreen.models.auth.dtos.UserDetailsImpl;
import com.gogreen.models.auth.enums.UserTypeEnum;
import com.gogreen.models.product.dtos.ProductDto;
import com.gogreen.models.product.dtos.ProductSearchDto;
import com.gogreen.models.product.entities.ProductEntity;
import com.gogreen.models.user.entities.VendorUserEntity;
import com.gogreen.models.vendor.entities.VendorEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductAdministrationService {
	private final ProductRepository productRepository;
	private final ProductMapper productMapper;

	private final ProductService productService;
	private final VendorRepository vendorRepository;

	private final VendorUserRepository vendorUserRepository;

	public Page<ProductDto> listProducts(Long vendorId, Pageable pageable) {
		ProductSearchDto productSearchDto = new ProductSearchDto();
		productSearchDto.setVendorId(vendorId);
		return this.productService.searchProducts(productSearchDto, pageable);
	}

	public ProductDto createProduct(ProductDto productDto, UserDetailsImpl userDetails) {

		VendorUserEntity vendorUserEntity = this.vendorUserRepository.findByIdAndDeletedFalseAndEnabledTrue(
				userDetails.getUserDetailsId()).orElseThrow(
				() -> new SystemException(HttpStatus.FORBIDDEN, "user not found"));

		ProductEntity productEntity = this.productMapper.toEntity(productDto);
		VendorEntity vendor = this.vendorRepository.findByIdAndDeletedFalse(
				vendorUserEntity.getVendorId()).orElseThrow(
				() -> new SystemException(HttpStatus.FORBIDDEN, "vendor is inActive"));
		productEntity.setVendor(vendor);
		productEntity = this.productRepository.saveAndFlush(productEntity);
		Set<ProductEntity> productEntities = vendor.getProducts();
		productEntities.add(productEntity);
		vendor.setProducts(productEntities);
		this.vendorRepository.saveAndFlush(vendor);
		return this.productMapper.toDto(productEntity);

	}
}

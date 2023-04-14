package com.gogreen.apis.product.control;

import com.gogreen.apis.product.boundaries.helper.ProductMapper;
import com.gogreen.apis.product.repositories.ProductRepository;
import com.gogreen.models.product.dtos.ProductDto;
import com.gogreen.models.product.dtos.ProductSearchDto;
import com.gogreen.models.product.entities.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepository;
	private final ProductMapper productMapper;

	public Page<ProductDto> searchProducts(ProductSearchDto productSearchDto,
			Pageable pageable) {
		Page<ProductEntity> productEntities;

		productEntities = this.productRepository.findByProductSearchDto(
				productSearchDto.getProductName() == null ?
						null :
						"%" + productSearchDto.getProductName() + "%",
				productSearchDto.getMaximumPrice(), productSearchDto.getMinimumPrice(),
				productSearchDto.getProductCategoryIds(), pageable);

		List<ProductDto> productDtos = productMapper.toDtoList(
				productEntities.getContent());

		Page productDtosPage = new PageImpl<>(productDtos, productEntities.getPageable(),
				productEntities.getNumberOfElements());

		return productDtosPage;

	}

}

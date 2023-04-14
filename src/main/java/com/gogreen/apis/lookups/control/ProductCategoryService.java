package com.gogreen.apis.lookups.control;

import com.gogreen.apis.lookups.boundary.util.mapper.ProductCategoryMapper;
import com.gogreen.apis.lookups.repository.ProductCategoryRepository;
import com.gogreen.models.product.dtos.ProductCategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductCategoryService {
	private final ProductCategoryRepository productCategoryRepository;
	private final ProductCategoryMapper productCategoryMapper;

	public List<ProductCategoryDto> retrieveProductCategories() {
		return this.productCategoryMapper.toDtoList(
				this.productCategoryRepository.findAll());
	}
}

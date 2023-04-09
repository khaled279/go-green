package com.gogreen.apis.product.control;

import com.gogreen.apis.product.boundaries.helper.ProductMapper;
import com.gogreen.apis.product.repositories.ProductRepository;
import com.gogreen.models.product.dtos.ProductDto;
import com.gogreen.models.product.dtos.ProductSearchDto;
import com.gogreen.models.product.entities.ProductEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepository;
	private final ProductMapper productMapper;

	public Page<ProductDto> searchProducts(ProductSearchDto productSearchDto,
			Pageable pageable) {
		Page<ProductEntity> productEntities;
		if (productSearchDto == null) {
			productEntities = this.productRepository.findByProductSearchDto(null, null,
					null, pageable);
		}
		else {
			productEntities = this.productRepository.findByProductSearchDto(
					productSearchDto.getProductName() == null ?
							null :
							"%" + productSearchDto.getProductName() + "%",
					productSearchDto.getPrice(), productSearchDto.getProductCategoryIds(),
					pageable);
		}

		List<ProductDto> productDtos = productMapper.toDtoList(
				productEntities.getContent());
		return new PageImpl<>(productDtos, productEntities.getPageable(),
				productEntities.getNumberOfElements());

	}

}

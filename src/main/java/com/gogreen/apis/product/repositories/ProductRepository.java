package com.gogreen.apis.product.repositories;

import com.gogreen.models.product.entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>,
		JpaSpecificationExecutor<ProductEntity> {
	@Query("SELECT p FROM ProductEntity p " + "WHERE (:productName IS NULL OR p.name LIKE %:productName%) " + "AND (:price IS NULL OR p.price < :price) " + "AND (COALESCE(:productIds, NULL) IS NULL OR p.id IN (:productIds)) " + "ORDER BY p.viewsCount")
	Page<ProductEntity> findByProductSearchDto(String productName, BigDecimal price,
			List<Long> productIds, Pageable pageable);
}

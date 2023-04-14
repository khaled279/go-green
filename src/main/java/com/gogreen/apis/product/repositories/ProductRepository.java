package com.gogreen.apis.product.repositories;

import com.gogreen.models.product.entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>,
		JpaSpecificationExecutor<ProductEntity> {
	@Query("SELECT p FROM ProductEntity p " + "WHERE (:productName IS NULL OR p.name LIKE %:productName%) " + "AND (:maximumPrice IS NULL OR p.price <= :maximumPrice) AND (:minimumPrice IS NULL OR p.price >= :minimumPrice)" + "AND (COALESCE(:productIds, NULL) IS NULL OR p.id IN (:productIds)) " + "ORDER BY p.viewsCount")
	Page<ProductEntity> findByProductSearchDto(@Param("productName") String productName,
			@Param("maximumPrice") BigDecimal maximumPrice,
			@Param("minimumPrice") BigDecimal minimumPrice,
			@Param("productIds") List<Long> productIds, Pageable pageable);

	Optional<ProductEntity> findByIdAndDeletedFalse(Long id);
}

package com.gogreen.models.product.entities;

import com.gogreen.models.base.entity.BaseEntity;
import com.gogreen.models.vendor.entities.VendorEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class ProductEntity extends BaseEntity {
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "price", nullable = false)
	private BigDecimal price;
	@Column(name = "discounted_price")
	private BigDecimal discountedPrice;
	@Column(name = "quantity", nullable = false)
	private Integer quantity;
	@Column(name = "rating")
	private Float rating;
	@Builder.Default
	@Column(name = "discounted")
	private boolean discounted = false;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vendor_id")
	private VendorEntity vendor;
}

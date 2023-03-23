package com.gogreen.models.vendor.entities;

import com.gogreen.models.base.entity.BaseEntity;
import com.gogreen.models.product.entities.ProductEntity;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "vendors")
public class VendorEntity extends BaseEntity {
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "vision")
	private String vision;
	@Column(name = "mission")
	private String mission;
	@OneToMany(mappedBy = "vendor", fetch = FetchType.LAZY)
	private Set<ProductEntity> products;
}

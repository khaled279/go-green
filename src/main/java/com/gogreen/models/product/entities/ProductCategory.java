package com.gogreen.models.product.entities;

import com.gogreen.models.base.entity.SuperBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "product_categories")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductCategory extends SuperBaseEntity {
	@Column(name = "name", nullable = false)
	private String name;

}

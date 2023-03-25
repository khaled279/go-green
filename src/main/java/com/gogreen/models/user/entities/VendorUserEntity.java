package com.gogreen.models.user.entities;

import com.gogreen.models.base.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vendor_users")
public class VendorUserEntity extends BaseEntity {
	@Column(nullable = false, name = "vendor_id")
	private Long vendorId;

}



package com.gogreen.models.cart.entities;

import com.gogreen.models.base.entity.SuperBaseEntity;
import com.gogreen.models.user.entities.CommunityUserEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart")
@SuperBuilder
public class CartEntity extends SuperBaseEntity {
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cartEntity")
	@Builder.Default
	private Set<CartItemEntity> items = new HashSet<>();
	private BigDecimal total;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private CommunityUserEntity user;
}

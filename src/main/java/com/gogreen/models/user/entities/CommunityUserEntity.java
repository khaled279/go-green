package com.gogreen.models.user.entities;

import com.gogreen.models.base.entity.BaseEntity;
import com.gogreen.models.cart.entities.CartEntity;
import com.gogreen.models.order.entities.OrderEntity;
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
@SuperBuilder
@Table(name = "community_users")
public class CommunityUserEntity extends BaseEntity {
	@Builder.Default
	private BigDecimal points = BigDecimal.ZERO;
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
	private CartEntity cartEntity;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	@Builder.Default
	private Set<OrderEntity> orders = new HashSet<>();
}

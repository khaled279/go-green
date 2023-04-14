package com.gogreen.models.user.entities;

import com.gogreen.models.base.entity.BaseEntity;
import com.gogreen.models.cart.entities.CartEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

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
}

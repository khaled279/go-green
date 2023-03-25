package com.gogreen.models.user.entities;

import com.gogreen.models.base.entity.BaseEntity;
import com.gogreen.models.cart.entities.Cart;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "community_users")
public class CommunityUserEntity extends BaseEntity {
	private Long points;
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
	private Cart cart;
}

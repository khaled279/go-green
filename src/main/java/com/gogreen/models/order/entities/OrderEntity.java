package com.gogreen.models.order.entities;

import com.gogreen.models.base.entity.ShippingInfo;
import com.gogreen.models.base.entity.SuperBaseEntity;
import com.gogreen.models.user.entities.CommunityUserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class OrderEntity extends SuperBaseEntity {
	@Column(name = "order_status")
	@Enumerated(value = EnumType.STRING)
	private OrderStatusEnum orderStatus;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	private Set<OrderItemEntity> items = new HashSet<>();
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private CommunityUserEntity user;

	private BigDecimal total;
	@Embedded
	private ShippingInfo shippingInfo;

}

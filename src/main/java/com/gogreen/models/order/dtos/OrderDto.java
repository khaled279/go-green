package com.gogreen.models.order.dtos;

import com.gogreen.models.base.dto.ShippingInfoDto;
import com.gogreen.models.base.dto.SuperBaseDto;
import com.gogreen.models.order.entities.OrderStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto extends SuperBaseDto {

	private OrderStatusEnum orderStatus;
	private Set<OrderItemDto> items = new HashSet<>();

	private BigDecimal total;

	private ShippingInfoDto shippingInfo;

	private String phoneNumber;
}



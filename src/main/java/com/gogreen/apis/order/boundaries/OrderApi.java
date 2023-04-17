package com.gogreen.apis.order.boundaries;

import com.gogreen.apis.order.control.OrderService;
import com.gogreen.models.auth.dtos.UserDetailsImpl;
import com.gogreen.models.base.dto.ShippingInfoDto;
import com.gogreen.models.base.entity.ShippingInfo;
import com.gogreen.models.order.dtos.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("community/user/order")
public class OrderApi {
	private final OrderService orderService;

	@PostMapping("")
	ResponseEntity<OrderDto> createOrder(@RequestBody @Valid ShippingInfoDto shippingInfo,
			@AuthenticationPrincipal UserDetailsImpl userDetails) {
		return ResponseEntity.ok(
				this.orderService.createOrder(userDetails, shippingInfo));
	}
}

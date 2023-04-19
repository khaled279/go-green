package com.gogreen.apis.order.boundaries;

import com.gogreen.apis.order.control.OrderService;
import com.gogreen.models.auth.dtos.UserDetailsImpl;
import com.gogreen.models.base.dto.ShippingInfoDto;
import com.gogreen.models.base.entity.ShippingInfo;
import com.gogreen.models.order.dtos.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

	@GetMapping("")
	ResponseEntity<Page<OrderDto>> listOrders(
			@AuthenticationPrincipal UserDetailsImpl userDetails,
			@RequestParam(required = false) Long pageSize,
			@RequestParam(required = false, defaultValue = "0") Long pageNo,
			@RequestParam(required = false, defaultValue = "DESC") Sort.Direction direction) {
		return ResponseEntity.ok(this.orderService.listOrders(userDetails,
				pageSize == null ?
						Pageable.unpaged() :
						PageRequest.of(pageNo.intValue(), pageSize.intValue())));
	}
}

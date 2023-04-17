package com.gogreen.apis.order.control;

import com.gogreen.apis.cart.controll.CartService;
import com.gogreen.apis.cart.repository.CartRepository;
import com.gogreen.apis.order.boundaries.util.mapper.CartOrderMapper;
import com.gogreen.apis.order.boundaries.util.mapper.OrderMapper;
import com.gogreen.apis.order.boundaries.util.mapper.ShippingInfoMapper;
import com.gogreen.apis.order.repositories.OrderItemRepository;
import com.gogreen.apis.order.repositories.OrderRepository;
import com.gogreen.apis.user.repository.CommunityUserRepository;
import com.gogreen.core.exception.SystemException;
import com.gogreen.models.auth.dtos.UserDetailsImpl;
import com.gogreen.models.base.dto.ShippingInfoDto;
import com.gogreen.models.base.entity.ShippingInfo;
import com.gogreen.models.cart.entities.CartEntity;
import com.gogreen.models.cart.entities.CartItemEntity;
import com.gogreen.models.order.dtos.OrderDto;
import com.gogreen.models.order.entities.OrderEntity;
import com.gogreen.models.order.entities.OrderStatusEnum;
import com.gogreen.models.user.entities.CommunityUserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
	private final OrderRepository orderRepository;
	private final CommunityUserRepository communityUserRepository;
	private final CartRepository cartRepository;

	private final CartService cartService;

	private final CartOrderMapper cartOrderMapper;

	private final OrderMapper orderMapper;

	private final OrderItemRepository orderItemRepository;

	private final ShippingInfoMapper shippingInfoMapper;

	public OrderDto createOrder(final UserDetailsImpl userDetails,
			ShippingInfoDto shippingInfo) {
		CommunityUserEntity communityUserEntity = this.communityUserRepository.findById(
				userDetails.getUserDetailsId()).orElseThrow(
				() -> new SystemException(HttpStatus.NOT_FOUND, "invalid user"));
		CartEntity cartEntity = communityUserEntity.getCartEntity();
		cartEntity.setTotal(recalculateCartTotal(cartEntity))
		;
		if (cartEntity.getItems().isEmpty()) {
			throw new SystemException(HttpStatus.NO_CONTENT, "cart is empty");

		}

		OrderEntity orderEntity = cartOrderMapper.toOrderEntity(cartEntity);
		orderEntity.setShippingInfo(this.shippingInfoMapper.toEntity(shippingInfo));
		orderEntity.setOrderStatus(OrderStatusEnum.PLACED);
		communityUserEntity.setPoints(
				communityUserEntity.getPoints().subtract(orderEntity.getTotal()));
		if (communityUserEntity.getPoints().compareTo(BigDecimal.ZERO) < 0) {
			throw new SystemException(HttpStatus.NOT_ACCEPTABLE, "Not enough points");
		}
		communityUserRepository.saveAndFlush(communityUserEntity);
		this.cartService.emptyCart(userDetails.getUserDetailsId());
		orderEntity = this.orderRepository.saveAndFlush(orderEntity);
		this.orderItemRepository.saveAllAndFlush(orderEntity.getItems());
		return this.orderMapper.toDto(orderEntity);
	}

	private BigDecimal recalculateCartTotal(CartEntity userCart) {
		Set<CartItemEntity> cartItems = userCart.getItems();
		BigDecimal cartTotal = BigDecimal.ZERO;
		for (CartItemEntity cartItem : cartItems) {
			cartTotal = cartTotal.add(cartItem.getProduct().isDiscounted() ?
					cartItem.getProduct().getDiscountedPrice()
							.multiply(new BigDecimal(cartItem.getQuantity())) :
					cartItem.getProduct().getPrice()
							.multiply(new BigDecimal(cartItem.getQuantity())));
		}
		return cartTotal;
	}
}

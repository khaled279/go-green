package com.gogreen.apis.cart.controll;

import com.gogreen.apis.cart.boundary.util.mapper.CartMapper;
import com.gogreen.apis.cart.repository.CartItemRepositiry;
import com.gogreen.apis.cart.repository.CartRepository;
import com.gogreen.apis.product.repositories.ProductRepository;
import com.gogreen.core.exception.SystemException;
import com.gogreen.models.cart.dto.CartDto;
import com.gogreen.models.cart.entities.CartEntity;
import com.gogreen.models.cart.entities.CartItemEntity;
import com.gogreen.models.product.entities.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
	private final CartRepository cartRepository;
	private final CartMapper cartMapper;
	private final ProductRepository productRepository;
	private final CartItemRepositiry cartItemRepositiry;

	public CartDto addProductToCart(Long productId, Long userId, Integer itemCount) {
		CartEntity userCart = this.cartRepository.findByUserId(userId).orElseThrow(
				() -> new SystemException(HttpStatus.NOT_FOUND,
						"Couldn't find User with Id: " + userId));
		ProductEntity productEntity = this.productRepository.findByIdAndDeletedFalse(
				productId).orElseThrow(() -> new SystemException(HttpStatus.NOT_FOUND,
				"Couldn't find Product with id:" + productId));
		Set<CartItemEntity> cartItems = userCart.getItems();
		for (CartItemEntity cartItem : cartItems) {
			if (cartItem.getProduct().getId().equals(productId)) {
				cartItem.setQuantity(cartItem.getQuantity() + itemCount);
				if (cartItem.getQuantity() > productEntity.getQuantity()) {
					throw new SystemException(HttpStatus.NOT_FOUND,
							"Message Out of Stock");
				}
				if (cartItem.getQuantity() <= 0) {
					cartItem.setQuantity(0);
					cartItems.remove(cartItem);
					cartItemRepositiry.delete(cartItem);
				}
				userCart.setItems(cartItems);
				userCart.setTotal(recalculateCartTotal(userCart));
				return updateCart(userCart);
			}
		}
		if (itemCount > productEntity.getQuantity()) {
			throw new SystemException(HttpStatus.NOT_FOUND, "Message Out of Stock");
		}
		CartItemEntity cartItem = CartItemEntity.builder().product(productEntity)
				.cartEntity(userCart).quantity(itemCount).build();
		cartItems.add(cartItem);
		userCart.setItems(cartItems);
		userCart.setTotal(recalculateCartTotal(userCart));
		return updateCart(userCart);
	}

	public CartDto retrieveCart(Long userId) {
		CartEntity userCart = this.cartRepository.findByUserId(userId).orElseThrow(
				() -> new SystemException(HttpStatus.NOT_FOUND,
						"Couldn't find User with Id: " + userId));
		return this.cartMapper.toDto(userCart);
	}

	private CartDto updateCart(CartEntity cartEntity) {
		this.cartItemRepositiry.saveAllAndFlush(cartEntity.getItems());
		return this.cartMapper.toDto(this.cartRepository.saveAndFlush(cartEntity));
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

	public void emptyCart(Long userId) {
		CartEntity userCart = this.cartRepository.findByUserId(userId).orElseThrow(
				() -> new SystemException(HttpStatus.NOT_FOUND,
						"Couldn't find User with Id: " + userId));
		Set<CartItemEntity> cartItems = userCart.getItems();
		userCart.setItems(new HashSet<>());
		userCart.setTotal(recalculateCartTotal(userCart));
		this.cartItemRepositiry.deleteAll(cartItems);
		this.cartRepository.saveAndFlush(userCart);

	}

}

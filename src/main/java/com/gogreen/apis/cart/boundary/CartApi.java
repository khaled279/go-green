package com.gogreen.apis.cart.boundary;

import com.gogreen.apis.cart.controll.CartService;
import com.gogreen.models.auth.dtos.UserDetailsImpl;
import com.gogreen.models.auth.enums.UserTypeEnum;
import com.gogreen.models.cart.dto.CartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("community/user/cart")
@RequiredArgsConstructor
public class CartApi {
	private final CartService cartService;

	@PostMapping("/add-product/{id}")
	ResponseEntity<CartDto> addProductToCart(@PathVariable(name = "id") Long productId,
			@RequestParam(required = false) Long userId,
			@RequestParam(required = false, defaultValue = "1") Integer itemCount,
			Authentication authentication) {
		UserDetailsImpl userDetails = ((UserDetailsImpl) authentication.getPrincipal());
		userId = userDetails.getUserType() == UserTypeEnum.ADMINISTRATION ?
				userId :
				userDetails.getUserDetailsId();
		return ResponseEntity.ok(
				this.cartService.addProductToCart(productId, userId, itemCount));
	}

	@GetMapping("")
	ResponseEntity<CartDto> addProductToCart(@RequestParam(required = false) Long userId,
			Authentication authentication) {
		UserDetailsImpl userDetails = ((UserDetailsImpl) authentication.getPrincipal());
		userId = userDetails.getUserType() == UserTypeEnum.ADMINISTRATION ?
				userId :
				userDetails.getUserDetailsId();
		return ResponseEntity.ok(this.cartService.retrieveCart(userId));
	}

	@DeleteMapping("")
	ResponseEntity<Void> emptyCart(@RequestParam(required = false) Long userId,
			Authentication authentication) {
		UserDetailsImpl userDetails = ((UserDetailsImpl) authentication.getPrincipal());
		userId = userDetails.getUserType() == UserTypeEnum.ADMINISTRATION ?
				userId :
				userDetails.getUserDetailsId();
		this.cartService.emptyCart(userId);
		return ResponseEntity.noContent().build();
	}

}

package com.gogreen.apis.admin.vendor.user.control;

import com.gogreen.apis.admin.vendor.user.repository.VendorUserRepository;
import com.gogreen.apis.user.boundary.util.mapper.GoGreenUserMapper;
import com.gogreen.apis.user.repository.GoGreenUserRepository;
import com.gogreen.core.exception.UserAlreadyExistsException;
import com.gogreen.models.auth.dtos.AuthenticationResponseDto;
import com.gogreen.models.auth.dtos.GoGreenUserDto;
import com.gogreen.models.auth.entities.GoGreenUserEntity;
import com.gogreen.models.auth.enums.UserTypeEnum;
import com.gogreen.models.cart.entities.CartEntity;
import com.gogreen.models.user.entities.VendorUserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class VendorUserService {
	private final VendorUserRepository vendorUserRepository;

	private final GoGreenUserRepository goGreenUserRepository;
	private final GoGreenUserMapper goGreenUserMapper;
	private final PasswordEncoder passwordEncoder;

	public AuthenticationResponseDto createUser(Long vendorId,
			GoGreenUserDto goGreenUserDto) {
		if (this.goGreenUserRepository.findByEmailAndDeletedIsFalse(
				goGreenUserDto.getEmail()).isPresent()) {
			throw new UserAlreadyExistsException();
		}
		GoGreenUserEntity goGreenUserEntity = goGreenUserMapper.toEntity(goGreenUserDto);
		goGreenUserEntity.setPassword(
				passwordEncoder.encode(goGreenUserDto.getPassword()));
		goGreenUserEntity.setUserTypeEnum(UserTypeEnum.VENDOR);
		VendorUserEntity vendorUserEntity = VendorUserEntity.builder().vendorId(vendorId)
				.build();
		vendorUserEntity = this.vendorUserRepository.saveAndFlush(vendorUserEntity);

		goGreenUserEntity.setUserDetailsId(vendorUserEntity.getId());
		this.goGreenUserRepository.saveAndFlush(goGreenUserEntity);

		return AuthenticationResponseDto.builder().email(goGreenUserDto.getEmail())
				.build();
	}

}

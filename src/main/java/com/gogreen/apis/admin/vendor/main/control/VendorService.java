package com.gogreen.apis.admin.vendor.main.control;

import com.gogreen.apis.admin.vendor.main.boundary.util.mapper.VendorMapper;
import com.gogreen.apis.admin.vendor.main.repository.VendorRepository;
import com.gogreen.core.exception.SystemException;
import com.gogreen.models.vendor.dtos.VendorDto;
import com.gogreen.models.vendor.entities.VendorEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VendorService {
	private final VendorRepository vendorRepository;
	private final VendorMapper vendorMapper;

	public VendorDto createVendor(VendorDto vendorDto) {
		VendorEntity vendorEntity = this.vendorMapper.toEntity(vendorDto);
		vendorEntity.setId(null);
		return this.vendorMapper.toDto(this.vendorRepository.saveAndFlush(vendorEntity));
	}

	public VendorDto retrieveVendor(Long id) {
		VendorEntity vendorEntity = this.vendorRepository.findByIdAndDeletedFalse(id)
				.orElseThrow(() -> new SystemException(HttpStatus.NOT_FOUND,
						"could find Vendor with id: " + id));
		return this.vendorMapper.toDto(vendorEntity);
	}

	public Page<VendorDto> listVendors(Pageable pageable) {
		Page<VendorEntity> vendorsPage = this.vendorRepository.findAll(pageable);
		return new PageImpl<>(this.vendorMapper.toDtoList(vendorsPage.getContent()),
				vendorsPage.getPageable(), vendorsPage.getTotalElements());
	}

	public VendorDto updateVendor(VendorDto vendorDto) {
		if (vendorDto.getId() == null) {
			throw new SystemException(HttpStatus.BAD_REQUEST,
					"vendor object must have an Id");
		}
		VendorEntity vendorEntity = this.vendorRepository.saveAndFlush(
				this.vendorMapper.toEntity(vendorDto));
		return this.vendorMapper.toDto(vendorEntity);
	}
}



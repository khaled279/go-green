package com.gogreen.models.base.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@SuperBuilder
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class ShippingInfo {
	@Column(name = "street_name")
	private String streetName;
	private String City;
	private String State;
	private String country;
	@Column(name = "building_number")
	private String buildingNumber;
	@Column(name = "apartment_number")
	private String apartmentNumber;
	@Column(name = "mobile_number")
	private String mobileNumber;
}

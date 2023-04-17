package com.gogreen.models.base.dto;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ShippingInfoDto {
	@NotBlank(message = "Street Name can't be Empty")
	private String streetName;
	@NotBlank(message = "City can't be Empty")
	private String City;
	private String State;
	private String country;
	@NotBlank(message = "building Number can't be Empty")
	private String buildingNumber;

	private String apartmentNumber;
	@NotBlank(message = "Mobile Number Is required")
	private String mobileNumber;
}

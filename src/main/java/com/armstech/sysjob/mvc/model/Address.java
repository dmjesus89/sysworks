package com.armstech.sysjob.mvc.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity(name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private AddressTypeEnum addresType;

	@NotNull
	@NotEmpty
	private String street;

	@NotNull
	private Integer number;

	@NotNull
	@NotEmpty
	private String neighborhood;

	@NotNull
	@NotEmpty
	private String city;

	@NotNull
	@NotEmpty
	private String state;

	@NotNull
	@NotEmpty
	private String country;

}

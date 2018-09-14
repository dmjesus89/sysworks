package com.armstech.sysjob.mvc.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity(name = "company")
public class Company {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@NotEmpty
	private String name;

	@Enumerated(EnumType.STRING)
	private CompanyTypeEnum companyType;
	
	@OneToOne
	private Address address;
	
	@OneToMany
	private List<Phone> phones;
	
	
	
	
	
	
}

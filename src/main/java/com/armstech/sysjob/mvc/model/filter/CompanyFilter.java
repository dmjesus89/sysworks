package com.armstech.sysjob.mvc.model.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CompanyFilter {

	
	private String name;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dtCadastro;

	//private List<Address> address;
	
	//private List<Phone> phones;
	
	
	
	
	
	
}

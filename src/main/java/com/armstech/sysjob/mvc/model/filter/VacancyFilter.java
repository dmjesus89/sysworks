package com.armstech.sysjob.mvc.model.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class VacancyFilter {

	private String description;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dtInsert;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dtStart;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dtEnd;

	private Boolean status;

}

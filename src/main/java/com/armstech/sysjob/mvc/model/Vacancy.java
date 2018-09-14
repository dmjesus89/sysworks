package com.armstech.sysjob.mvc.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity(name = "vacancy")
public class Vacancy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@NotEmpty
	private String description;

	@Column(name = "dt_insert")
	private LocalDate dtInsert;

	@Column(name = "dt_start")
	private LocalDate dtStart;

	@Column(name = "dt_end")
	private LocalDate dtEnd;

	private Boolean status;

}

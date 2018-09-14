package com.armstech.sysjob.mvc.repository;

import org.springframework.data.repository.CrudRepository;

import com.armstech.sysjob.mvc.model.Vacancy;
import com.armstech.sysjob.mvc.repository.query.VacancyRepositoryQuery;

public interface VacancyRepository extends CrudRepository<Vacancy, Long>, VacancyRepositoryQuery {

}

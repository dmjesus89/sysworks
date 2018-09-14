package com.armstech.sysjob.mvc.repository.query;

import java.util.List;

import com.armstech.sysjob.mvc.model.Vacancy;
import com.armstech.sysjob.mvc.model.filter.VacancyFilter;

public interface VacancyRepositoryQuery {

	public List<Vacancy> getByFilter(VacancyFilter vacancyFilter);

}

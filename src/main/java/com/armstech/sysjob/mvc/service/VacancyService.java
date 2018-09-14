package com.armstech.sysjob.mvc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.armstech.sysjob.api.exception.CompanyNotFoudException;
import com.armstech.sysjob.mvc.model.Company;
import com.armstech.sysjob.mvc.model.Vacancy;
import com.armstech.sysjob.mvc.model.filter.VacancyFilter;
import com.armstech.sysjob.mvc.repository.CompanyRepository;
import com.armstech.sysjob.mvc.repository.VacancyRepository;

@Service
public class VacancyService {

	@Autowired
	private VacancyRepository vacancyRepository;

	@Autowired
	private CompanyRepository companyRepository;

	public List<Vacancy> getVacancys() {
		List<Vacancy> listVacancy = (List<Vacancy>) vacancyRepository.findAll();
		return listVacancy;
	}

	public List<Vacancy> getVacancysByFilter(VacancyFilter vacancyFilter) {
		return this.vacancyRepository.getByFilter(vacancyFilter);
	}

	public Vacancy getVacancyById(Long id) {
		Optional<Vacancy> option = vacancyRepository.findById(id);
		if (null != option && option.get() != null) {
			return option.get();
		}
		throw new EmptyResultDataAccessException(1);
	}

	public Vacancy save(Vacancy vacancy) {
	//	Optional<Company> company = companyRepository.findById(vacancy.getCompany().getId());
	//	if (company == null || company.get() == null) {
	//		throw new CompanyNotFoudException();
	//	}
		vacancyRepository.save(vacancy);
		return vacancy;
	}

	public Vacancy update(Long id, Vacancy vacancy) {
		Vacancy vacancySaved = this.getVacancyById(id);
		BeanUtils.copyProperties(vacancy, vacancySaved, "id");
		vacancyRepository.save(vacancy);
		return vacancySaved;
	}

}

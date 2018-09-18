package com.armstech.sysjob.mvc.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.armstech.sysjob.mvc.model.Company;
import com.armstech.sysjob.mvc.model.filter.CompanyFilter;
import com.armstech.sysjob.mvc.repository.CompanyRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CompanyServiceImpl {

	@Autowired
	private CompanyRepository companyRepository;

	public List<Company> getCompanys() {
		List<Company> listCompany = (List<Company>) companyRepository.findAll();
		return listCompany;
	}

	public Page<Company> getByFilter(CompanyFilter companyFilter, Pageable page) {
		return this.companyRepository.getByFilter(companyFilter, page);
	}

	public Company getCompanyById(Long id) {
		Optional<Company> option = companyRepository.findById(id);
		if (null != option && option.get() != null) {
			return option.get();
		}
		throw new EmptyResultDataAccessException(1);
	}

	public Company save(@Valid Company company) {
		company.setDtCadastro(LocalDate.now());
		return companyRepository.save(company);
	}

	public @Valid Company update(Long id, Company company) {
		Company companySaved = getCompanyById(id);
		BeanUtils.copyProperties(company, companySaved, "id");
		companyRepository.save(companySaved);
		return companySaved;
	}

}

package com.armstech.sysjob.mvc.repository.query;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.armstech.sysjob.mvc.model.Company;
import com.armstech.sysjob.mvc.model.filter.CompanyFilter;

public interface CompanyRepositoryQuery {

	public Page<Company> getByFilter(CompanyFilter companyFilter, Pageable page);

}

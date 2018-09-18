package com.armstech.sysjob.mvc.repository;

import org.springframework.data.repository.CrudRepository;

import com.armstech.sysjob.mvc.model.Company;
import com.armstech.sysjob.mvc.repository.query.CompanyRepositoryQuery;

public interface CompanyRepository extends CrudRepository<Company, Long>, CompanyRepositoryQuery {


}

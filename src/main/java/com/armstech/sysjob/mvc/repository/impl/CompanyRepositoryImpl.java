package com.armstech.sysjob.mvc.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.armstech.sysjob.mvc.model.Company;
import com.armstech.sysjob.mvc.model.Company_;
import com.armstech.sysjob.mvc.model.filter.CompanyFilter;
import com.armstech.sysjob.mvc.repository.query.CompanyRepositoryQuery;

public class CompanyRepositoryImpl implements CompanyRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Company> getByFilter(CompanyFilter companyFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Company> criteria = builder.createQuery(Company.class);

		Root<Company> root = criteria.from(Company.class);

		Predicate[] predicates = criarRestriction(companyFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Company> query = manager.createQuery(criteria);

		addPaginator(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(companyFilter));
	}

	private Predicate[] criarRestriction(CompanyFilter companyFilter, CriteriaBuilder builder, Root<Company> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(companyFilter.getName())) {
			predicates.add(builder.like(builder.lower(root.get(Company_.name)),
					"%" + companyFilter.getName().toLowerCase() + "%"));
		}

		if (companyFilter.getDtCadastro() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(Company_.dtCadastro), companyFilter.getDtCadastro()));
			predicates.add(builder.lessThanOrEqualTo(root.get(Company_.dtCadastro), companyFilter.getDtCadastro()));
		}

		return predicates.toArray(new Predicate[predicates.size()]);

	}

	private Long total(CompanyFilter companyFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Company> root = criteria.from(Company.class);

		Predicate[] predicates = criarRestriction(companyFilter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();

	}

	private void addPaginator(TypedQuery<Company> query, Pageable pageable) {
		int pageActual = pageable.getPageNumber();
		int totalRegistroForPage = pageable.getPageSize();
		int firstItenForPage = pageActual * totalRegistroForPage;
		query.setFirstResult(firstItenForPage);
		query.setMaxResults(totalRegistroForPage);

	}

}

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

import org.springframework.util.StringUtils;

import com.armstech.sysjob.mvc.model.Vacancy;
import com.armstech.sysjob.mvc.model.Vacancy_;
import com.armstech.sysjob.mvc.model.filter.VacancyFilter;
import com.armstech.sysjob.mvc.repository.query.VacancyRepositoryQuery;

public class VacancyRepositoryImpl implements VacancyRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Vacancy> getByFilter(VacancyFilter vacancyFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Vacancy> criteria = builder.createQuery(Vacancy.class);

		Root<Vacancy> root = criteria.from(Vacancy.class);

		Predicate[] predicates = criarRestriction(vacancyFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Vacancy> query = manager.createQuery(criteria);
		return query.getResultList();
	}

	private Predicate[] criarRestriction(VacancyFilter vacancyFilter, CriteriaBuilder builder, Root<Vacancy> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(vacancyFilter.getDescription())) {
			predicates.add(builder.like(builder.lower(root.get(Vacancy_.description)),
					"%" + vacancyFilter.getDescription().toLowerCase() + "%"));
		}

		if (vacancyFilter.getDtInsert() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(Vacancy_.dtStart), vacancyFilter.getDtStart()));
		}
		if (vacancyFilter.getDtEnd() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get(Vacancy_.dtEnd), vacancyFilter.getDtEnd()));
		}
		return predicates.toArray(new Predicate[predicates.size()]);

	}

}

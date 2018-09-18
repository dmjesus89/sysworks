package com.armstech.sysjob.mvc.model;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Vacancy.class)
public abstract class Vacancy_ {

	public static volatile SingularAttribute<Vacancy, LocalDate> dtInsert;
	public static volatile SingularAttribute<Vacancy, String> description;
	public static volatile SingularAttribute<Vacancy, Long> id;
	public static volatile SingularAttribute<Vacancy, LocalDate> dtStart;
	public static volatile SingularAttribute<Vacancy, LocalDate> dtEnd;
	public static volatile SingularAttribute<Vacancy, Boolean> status;

}


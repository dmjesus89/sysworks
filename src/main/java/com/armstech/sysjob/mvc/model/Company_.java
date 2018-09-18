package com.armstech.sysjob.mvc.model;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Company.class)
public abstract class Company_ {

	public static volatile SingularAttribute<Company, Address> address;
	public static volatile SingularAttribute<Company, CompanyTypeEnum> companyType;
	public static volatile SingularAttribute<Company, LocalDate> dtCadastro;
	public static volatile SingularAttribute<Company, String> name;
	public static volatile ListAttribute<Company, Phone> phones;
	public static volatile SingularAttribute<Company, Long> id;

}


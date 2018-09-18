package com.armstech.sysjob.mvc.model;

import com.armstech.sysjob.mvc.model.security.UserRole;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SetAttribute<User, UserRole> userRoles;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, Address> address;
	public static volatile SingularAttribute<User, String> mail;
	public static volatile SingularAttribute<User, LocalDate> dtCadastro;
	public static volatile SingularAttribute<User, String> cpf;
	public static volatile SingularAttribute<User, Long> id;
	public static volatile SingularAttribute<User, Boolean> enabled;
	public static volatile SingularAttribute<User, String> username;

}


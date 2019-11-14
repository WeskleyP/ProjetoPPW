package com.crm.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Role.class)
public abstract class Role_ {

	public static volatile SingularAttribute<Role, String> nomeRole;
	public static volatile SingularAttribute<Role, Long> idRole;
	public static volatile ListAttribute<Role, Usuario> usuarios;

	public static final String NOME_ROLE = "nomeRole";
	public static final String ID_ROLE = "idRole";
	public static final String USUARIOS = "usuarios";

}


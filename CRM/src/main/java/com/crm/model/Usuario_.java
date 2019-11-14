package com.crm.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Usuario.class)
public abstract class Usuario_ {

	public static volatile SingularAttribute<Usuario, String> password;
	public static volatile SingularAttribute<Usuario, Boolean> ativo;
	public static volatile ListAttribute<Usuario, Role> role;
	public static volatile SingularAttribute<Usuario, Date> lastlogin;
	public static volatile SingularAttribute<Usuario, Long> idUsuario;
	public static volatile SingularAttribute<Usuario, String> emailUsuario;
	public static volatile SingularAttribute<Usuario, String> username;

	public static final String PASSWORD = "password";
	public static final String ATIVO = "ativo";
	public static final String ROLE = "role";
	public static final String LASTLOGIN = "lastlogin";
	public static final String ID_USUARIO = "idUsuario";
	public static final String EMAIL_USUARIO = "emailUsuario";
	public static final String USERNAME = "username";

}


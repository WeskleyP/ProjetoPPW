package com.crm.model;

import com.crm.model.enumarate.TipoContato;
import com.crm.model.enumarate.TipoTelefone;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Telefone.class)
public abstract class Telefone_ {

	public static volatile SingularAttribute<Telefone, Cliente> cliente;
	public static volatile SingularAttribute<Telefone, TipoTelefone> tipoTelefone;
	public static volatile SingularAttribute<Telefone, TipoContato> tipoContato;
	public static volatile SingularAttribute<Telefone, String> numeroTelefone;
	public static volatile SingularAttribute<Telefone, Long> id;

	public static final String CLIENTE = "cliente";
	public static final String TIPO_TELEFONE = "tipoTelefone";
	public static final String TIPO_CONTATO = "tipoContato";
	public static final String NUMERO_TELEFONE = "numeroTelefone";
	public static final String ID = "id";

}

